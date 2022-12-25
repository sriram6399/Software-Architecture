from sqlalchemy import Table, Column
from sqlalchemy.sql.sqltypes import Integer,String,Float
from config.db import meta

userlists = Table(
    'user_list',meta, 
    Column('id',Integer,primary_key = True),
    Column('for_sale',Integer),
    Column('my_cart',Integer),
    Column('past_purchases',Integer),
    Column('recommended_items',Integer),
    Column('user_id',Integer)
    )