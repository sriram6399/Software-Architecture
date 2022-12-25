from sqlalchemy import Table, Column
from sqlalchemy.sql.sqltypes import Integer,String,Float
from config.db import meta

lists = Table(
    'lists',meta, 
    Column('list_id',Integer,primary_key = True),
    Column('title',String(255)),
    Column('description', String(2550)),
    Column('list_type',String(255)),
    Column('owner_id',Integer)
    )