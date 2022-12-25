from fastapi import APIRouter
from models.item import items
from config.db import conn
from schemas.item import Item

item = APIRouter()

@item.get("/items/getallitems")
async def getallusers():
    return conn.execute(items.select()).fetchall()

@item.get("/items/getitemsbyid/{id}")
async def getitemsbyid(id:int):
    return conn.execute(items.select().where(items.c.item_id == id)).fetchall()

@item.post("/items/additem")
async def additem(item: Item):
    conn.execute(items.insert().values(
        title = item.title,
        description = item.description,
        price = item.price,
        qty_avail = item.qty_avail,
        qty_sold = item.qty_sold,
        seller_id = item.seller_id
    ))
    return conn.execute(items.select()).fetchall()

@item.put("/items/updateitem/{id}")
async def updateitem(id:int, item: Item):
    conn.execute(items.update().values(
        title = item.title,
        description = item.description,
        price = item.price,
        qty_avail = item.qty_avail,
        qty_sold = item.qty_sold,
        seller_id = item.seller_id
    ).where(items.c.item_id == id))
    return conn.execute(items.select()).fetchall()

@item.delete("/items/deleteitem/{id}")
async def deleteitem(id:int):
    conn.execute(items.delete().where(items.c.item_id == id))
    return conn.execute(items.select()).fetchall()