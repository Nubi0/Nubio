from fastapi import FastAPI
from app import database
app = FastAPI()

@app.get("/")
async def root():
    return {"message": "Hello World"}

@app.get("/get-all")
def get_all():
    data = database.all()
    return {"data" : data}
