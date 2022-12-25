from sqlalchemy import Table, Column
from sqlalchemy.sql.sqltypes import Integer,String,Float
from config.db import meta

items = Table(
    'items',meta, 
    Column('item_id',Integer,primary_key = True),
    Column('title',String(255)),
    Column('description', String(2550)),
    Column('price',Float),
    Column('qty_avail',Integer),
    Column('qty_sold',Integer),
    Column('seller_id',Integer)
    )