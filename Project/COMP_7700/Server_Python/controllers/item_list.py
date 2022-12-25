from fastapi import APIRouter
from models.item_list import itemlists
from config.db import conn
from schemas.item_list import ItemList

item_list = APIRouter()

@item_list.get("/itemlists/getallitemlists")
async def getallitemlists():
    return conn.execute(itemlists.select()).fetchall()

@item_list.get("/itemlists/getitemlistsbyid/{id}")
async def getitemlistsbyid(id:int):
    return conn.execute(itemlists.select().where(itemlists.c.id == id)).fetchall()

@item_list.post("/itemlists/additemlist")
async def additemlist(itemlist: ItemList):
    conn.execute(itemlists.insert().values(
        id_of_item = itemlist.id_of_item,
        id_of_list = itemlist.id_of_list,
        qty_of_item = itemlist.qty_of_item
    ))
    return conn.execute(itemlists.select()).fetchall()

@item_list.put("/itemlists/updateitemlist/{id}")
async def updateitemlist(id:int, itemlist:ItemList):
    conn.execute(itemlists.update().values(
        id_of_item = itemlist.id_of_item,
        id_of_list = itemlist.id_of_list,
        qty_of_item = itemlist.qty_of_item
    ).where(itemlists.c.id == id))
    return conn.execute(itemlists.select()).fetchall()

@item_list.delete("/itemlists/deleteitemlist/{id}")
async def deleteitemlist(id:int):
    conn.execute(itemlists.delete().where(itemlists.c.id == id))
    return conn.execute(itemlists.select()).fetchall()
    