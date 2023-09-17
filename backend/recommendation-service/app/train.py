import pandas as pd
import gensim.models.word2vec as word2vec
from gensim.models import FastText

import sys
sys.stdout.reconfigure(encoding='utf-8')
words = pd.read_csv('C:/Users/김민규/Desktop/nubio/backend/recommendation-service/MOCK_DATA.csv')
# print(words)
from random import shuffle
corpus = []
for i in words.values.tolist():
  shuffle(i)
  corpus.append(i)
corpus

# b2v = word2vec.Word2Vec(words, window=7,vector_size=100, min_count=0,sg=1,workers=4,sample=0.000001)
b2v = FastText(corpus, vector_size=100, window=5, min_count=1, sg=1, workers=4)
# print(b2v.wv)

# b2v.wv.syn0.shape
def recommend1(search, model, topn=5):
    similar=[]

    for first_name in words['first_name'] :
        similarity = 0
        try:
            similarity = model.wv.similarity(search, first_name)

        except Exception as e:
            pass

        if first_name == search:
            continue

        similar.append((first_name,similarity))

    similar = list(set(similar))
    similar = pd.DataFrame(similar,columns=['first_name','score'])

    return similar.sort_values(by='score',ascending=False)[:topn]


produc_lsit=words['first_name'].unique().tolist()
produc_lsit

# 추천 함수 정의
def recommend2(model,positiveVal,negativeVal=[]):
  reco_list=[]
  # for i in model.wv.most_similar(positive=positiveVal, negative=negativeVal, topn=5):
  #   reco_list.append(i[0])
  similar_words = model.wv.most_similar(positive=positiveVal, negative=negativeVal, topn=5)
  reco_list = [word for word, _ in similar_words]
  result=[s for s in reco_list if any(xs in s for xs in produc_lsit) ]
  return result

reco1=recommend1('Kim', b2v) ['first_name'].tolist()
print(reco1)
reco2=recommend2(b2v,positiveVal=['kim','Mattocks','-57','-25','Itauguá','Female'] )
print(reco2)