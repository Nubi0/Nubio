import pandas as pd
import gensim.models.word2vec as word2vec
from gensim.models import FastText
import pickle
import sys


sys.stdout.reconfigure(encoding='utf-8')
words = pd.read_csv('C:/Users/김민규/Desktop/nubio/backend/recommendation-service/MOCK_DATA.csv')
# print(words)
from random import shuffle
corpus = []
for i in words.values.tolist():
  shuffle(i)
  corpus.append(i)

# b2v = word2vec.Word2Vec(words, window=7,vector_size=100, min_count=0,sg=1,workers=4,sample=0.000001)
b2v = FastText(corpus, vector_size=100, window=5, min_count=1, sg=0, workers=4)

def recommend1(search, model, topn=5):
    similar=[]

    for city in words['city'] :
        similarity = 0
        try:
            similarity = model.wv.similarity(search, city)

        except Exception as e:
            pass

        if city == search:
            continue

        similar.append((city,similarity))

    similar = list(set(similar))
    similar = pd.DataFrame(similar,columns=['city','score'])

    return similar.sort_values(by='score',ascending=False)[:topn]


# 결과값 나올 컬럼
produc_list=words['city'].unique().tolist()

# 컬럼 정보 파일로 저장
# with open('produc_list.pkl', 'wb') as file:
#     pickle.dump(produc_list, file)
    
# 컬럼 정보 불러오기
# with open('produc_list.pkl', 'rb') as file:
#     loaded_produc_list = pickle.load(file)
    

# 추천 함수 정의
def recommend2(model,positiveVal,negativeVal=[]):
  reco_list=[]
  similar_words = model.wv.most_similar(positive=positiveVal, negative=negativeVal, topn=5)
  reco_list = [word for word, _ in similar_words]
  result=[s for s in reco_list if any(xs in s for xs in produc_list) ]
  return result

# # 학습모델 저장, 불러오기
# b2v.save("fasttext_model")
# loaded_model = FastText.load("fasttext_model")

# reco1=recommend1('Jacenta', b2v) ['city'].tolist()
# print(reco1)
# reco2=recommend2(b2v,positiveVal=['Sianna','Wadham','Female','Showler','Manjiang','Nacka'] )
# print(reco2)
# reco3=recommend2(loaded_model,positiveVal=['Sianna','Wadham','Female'] )
# print(reco3)


# 추천 학습모델 만들 때 사용
# db에서 다 조회해서 리스트화, 벡터화, 저장 해야함
def makeModel(data:list):
    words = []
    result_list = []  
    for row in data:
        enjoyList = row.get('enjoyList', [])  # 'enjoyList' 키가 없으면 빈 리스트 반환
        pk = row.get('pk', None)  # 'pk' 키가 없으면 None 반환

        if len(enjoyList) < 2 or pk is None:
            continue

        enjoyList.append(pk)
        result_list.append(pk)
        shuffle(enjoyList)
        words.append(enjoyList)
    with open('course_pk', 'wb') as file:
        pickle.dump(result_list, file)
    print(result_list)
    print(words)
    
    
# 입력 리스트가 들어오면 저장되있던 모델 불러와서 유사한 코스 출력
def recoCourse(data):    
    with open('course_pk', 'rb') as file:
        course_pk = pickle.load(file)
    
    
# 시간비교(모델 바로 생성, 모델 저장한 것 사용) 대충 2.7배? 빨랐음
# import timeit

# def method_a():
#     b2v = FastText(corpus, vector_size=100, window=5, min_count=1, sg=0, workers=4)
#     reco2=recommend2(b2v,positiveVal=['Sianna','Wadham','Female','Showler','Manjiang','Nacka'] )
#     print(reco2)
#     pass

# def method_b():
#     loaded_model = FastText.load("fasttext_model")
#     reco3=recommend2(loaded_model,positiveVal=['Sianna','Wadham','Female','Showler','Manjiang','Nacka'] )
#     print(reco3)
#     pass

# # timeit을 사용하여 각 방법의 실행 시간을 측정합니다.
# number_of_iterations = 3  # 실행 횟수

# time_a = timeit.timeit(method_a, number=number_of_iterations)
# time_b = timeit.timeit(method_b, number=number_of_iterations)

# # 결과를 출력합니다.
# print(f"Method A 소요 시간: {time_a} 초")
# print(f"Method B 소요 시간: {time_b} 초")

# # 어떤 방법이 빠른지 비교합니다.
# if time_a < time_b:
#     print("Method A가 Method B보다 빠릅니다.")
# elif time_a > time_b:
#     print("Method B가 Method A보다 빠릅니다.")
# else:
#     print("Method A와 Method B의 실행 시간이 같습니다.")