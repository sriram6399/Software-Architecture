from fastapi import APIRouter
from models.user import users
from config.db import conn
from schemas.user import User

user = APIRouter()

@user.get("/users/getallusers")
async def getallusers():
    return conn.execute(users.select()).fetchall()

@user.get("/users/getuserbyid/{id}")
async def getusersbyid(id:int):
    return conn.execute(users.select().where(users.c.user_id == id)).fetchall()

@user.post("/users/adduser")
async def adduser(user: User):
    conn.execute(users.insert().values(
        user_name = user.user_name,
        password = user.password
    ))
    return conn.execute(users.select()).fetchall()

@user.put("/users/updateuser/{id}")
async def updateuser(id:int, user: User):
    conn.execute(users.update().values(
        user_name = user.user_name,
        password = user.password
    ).where(users.c.user_id == id))
    return conn.execute(users.select()).fetchall()

@user.delete("/users/deleteuser/{id}")
async def deleteuser(id:int):
    conn.execute(users.delete().where(users.c.user_id == id))
    return conn.execute(users.select()).fetchall()
