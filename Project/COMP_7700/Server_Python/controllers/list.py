from fastapi import APIRouter
from models.list import lists
from config.db import conn
from schemas.list import List

list = APIRouter()

@list.get("/lists/getalllists")
async def getalllists():
    return conn.execute(lists.select()).fetchall()

@list.get("/lists/getlistsbyid/{id}")
async def getlistsbyid(id:int):
    return conn.execute(lists.select().where(lists.c.list_id == id)).fetchall()

@list.post("/lists/addlist")
async def addlist(list: List):
    conn.execute(lists.insert().values(
        title = list.title,
        description = list.description,
        list_type = list.list_type,
        owner_id = list.owner_id
    ))
    return conn.execute(lists.select()).fetchall()

@list.put("/lists/updatelist/{id}")
async def updatelist(id:int, list:List):
    conn.execute(lists.update().values(
        title = list.title,
        description = list.description,
        list_type = list.list_type,
        owner_id = list.owner_id
    ).where(lists.c.list_id == id))
    return conn.execute(lists.select()).fetchall()

@list.delete("/lists/deletelist/{id}")
async def deletelist(id:int):
    conn.execute(lists.delete().where(lists.c.list_id == id))
    return conn.execute(lists.select()).fetchall()
    