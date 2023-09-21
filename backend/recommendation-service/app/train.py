import pandas as pd
import sys
import gensim.models.word2vec as word2vec
import pickle
from gensim.models import KeyedVectors
from random import shuffle

# sys.stdout.reconfigure(encoding='utf-8')
# words = pd.read_csv('C:/Users/SSAFY/Desktop/nubio/backend/recommendation-service/MOCK_DATA.csv')
# print(words)
# corpus = []
# for i in words.values.tolist():
#   shuffle(i)
#   corpus.append(i)
# b2v = word2vec.Word2Vec(corpus, window=7,vector_size=100, min_count=0,sg=1,workers=4,sample=0.000001)


# 추천 함수 정의
def recommend(model,positiveVal,negativeVal=[], produc_list=[],dataset={}):
    pos = []
    for i in positiveVal:
        if i in dataset:
            pos.append(i)
            # print(i)

    # 만약 모든 입력이 존재하지 않는다면 일정한 입력 미리 넣기
    if len(pos) == 0:
        pos.append("고기")
        
    reco_list=[]
    similar_words = model.most_similar(positive=pos, negative=negativeVal, topn=50, restrict_vocab=None)
    print(similar_words)
    reco_list = [word for word, _ in similar_words]
    result = [s for s in map(str, reco_list) if any(str(x) in s for x in map(str, produc_list))]
    return result
    

# 추천 학습모델 만들 때 사용
# db에서 다 조회해서 리스트화, 벡터화, 저장 해야함
def makeModel(data:list, region:str):
    words = []
    result_list = []  
    result_set = set()
    for row in data:
        enjoyList = row.get('enjoyList', [])  # 'enjoyList' 키가 없으면 빈 리스트 반환
        pk = row.get('pk', None)  # 'pk' 키가 없으면 None 반환

        if len(enjoyList) < 2 or pk is None:
            continue
        # 연관도 높이기 위해서
        for i in range(len(enjoyList)):
            enjoyList.append(pk)
            
        # 조회시 시간복잡도 줄이기 위해서
        for i in enjoyList:
            result_set.add(i)
            
        # 코스의 id값 저장 리스트
        result_list.append(pk)
        shuffle(enjoyList)
        words.append(enjoyList)
        # print(words)
        print(len(words))
        
    # 코스 id값 리스트 저장
    with open(f'{region}_course_list', 'wb') as file:
        pickle.dump(result_list, file)
    with open(f'{region}_dataset', 'wb') as file:
        pickle.dump(result_set, file)
    b2v = word2vec.Word2Vec(words, window=5,vector_size=100, min_count=0,sg=0,workers=4,sample=0.00001,epochs=100)
    b2v.wv.save_word2vec_format(f'{region}_w2v_model',binary=False)

    
# 입력 리스트가 들어오면 저장되있던 모델 불러와서 유사한 코스 출력
def recoCourse(data):    
    with open(f'{data.region}_course_list', 'rb') as file:
        course_pk = pickle.load(file)
    with open(f'{data.region}_dataset', 'rb') as file:
        dataset = pickle.load(file)
    loaded_model = KeyedVectors.load_word2vec_format(f'{data.region}_w2v_model')
    reco=recommend(loaded_model,positiveVal=data.words, produc_list=course_pk, dataset=dataset)
    return reco


# https://projector.tensorflow.org/ 시각화
# from gensim.scripts.glove2word2vec import glove2word2vec
# python -m gensim.scripts.word2vec2tensor -i '경로' -o new_w2v