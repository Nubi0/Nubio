from fastapi import FastAPI
from app import database
from app import train
from pydantic import BaseModel
from typing import List

app = FastAPI()


class InputData(BaseModel):
    words: List[str]
    
@app.get("/")
async def root():
    return {"message": "Hello World"}

# db불러와서 학습모델 저장
@app.get("/get-all")
def get_all():
    data = database.all()
    train.recoCourse(data)
    return {"data" : data}

# 입력한 정보와 비슷한 코스 응답
@app.post("/get-list")
async def process_words(request: InputData):
    words = request.words
    return {"result": words}