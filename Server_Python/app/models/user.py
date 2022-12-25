from sqlalchemy import Table, Column
from sqlalchemy.sql.sqltypes import Integer,String
from config.db import meta

users = Table(
    'users',meta, 
    Column('user_id',Integer,primary_key = True),
    Column('user_name',String(255)),
    Column('password', String(255))
    )