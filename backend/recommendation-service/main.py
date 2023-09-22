from fastapi import FastAPI
from app import database
from app import train
from pydantic import BaseModel
from typing import List

app = FastAPI()


class InputData(BaseModel):
    region: str
    words: List[str]
    
class Region(BaseModel):
    region: str   
    
@app.get("/")
async def root():
    return {"message": "Hello World"}

# db불러와서 학습모델 저장
# 받아온 지역으로 다른이름으로 모델 저장
@app.post("/get-all")
def get_all(request: Region):
    data = database.all()
    train.makeModel(data, request.region)
    database.remove()
    return {"response" : "success"}

# 입력한 정보와 비슷한 코스 응답
# 받아온 지역으로 해당지역 모델 불러와서 추천
@app.post("/get-list")
async def process_words(request: InputData):
    words = train.recoCourse(request)
    return {"result": words}

@app.get("/delete")
def delete():
    database.remove()
    return {"data" : "delete"}