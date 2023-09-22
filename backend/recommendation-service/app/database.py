import pymongo
import os
from dotenv import load_dotenv

load_dotenv()
mongoURL = os.getenv("MONGO_URL")
mongoNAME = os.getenv("MONGO_NAME")

client = pymongo.MongoClient(mongoURL)
db = client["nubio"]
col = db["words"]

def all():
    response = col.find({})
    data = [{"data":"123"}]
    for i in response:
        i["_id"] = str(i["_id"])
        data.append(i)
    return data

def remove():
    col.delete_many({})