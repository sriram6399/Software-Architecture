from fastapi import APIRouter
from models.user_list import userlists
from config.db import conn
from schemas.user_list import UserList

user_list = APIRouter()

@user_list.get("/userlists/getalluserlists")
async def getalluserlists():
    return conn.execute(userlists.select()).fetchall()

@user_list.get("/userlists/getuserlistsbyid/{id}")
async def getuserlistsbyid(id:int):
    return conn.execute(userlists.select().where(userlists.c.id == id)).fetchall()

@user_list.post("/userlists/adduserlist")
async def adduserlist(userlist: UserList):
    conn.execute(userlists.insert().values(
        for_sale = userlist.for_sale,
        my_cart = userlist.my_cart,
        past_purchases = userlist.past_purchases,
        recommended_items = userlist.recommended_items,
        user_id = userlist.user_id
    ))
    return conn.execute(userlists.select()).fetchall()

@user_list.put("/userlists/updateuserlist/{id}")
async def updateuserlist(id:int, userlist:UserList):
    conn.execute(userlists.update().values(
        for_sale = userlist.for_sale,
        my_cart = userlist.my_cart,
        past_purchases = userlist.past_purchases,
        recommended_items = userlist.recommended_items,
        user_id = userlist.user_id
    ).where(userlists.c.id == id))
    return conn.execute(userlists.select()).fetchall()

@user_list.delete("/userlists/deleteuserlist/{id}")
async def deleteuserlist(id:int):
    conn.execute(userlists.delete().where(userlists.c.id == id))
    return conn.execute(userlists.select()).fetchall()
    