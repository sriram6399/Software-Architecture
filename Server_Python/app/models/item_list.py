from sqlalchemy import Table, Column
from sqlalchemy.sql.sqltypes import Integer,String,Float
from config.db import meta

itemlists = Table(
    'item_list_pair',meta, 
    Column('id',Integer,primary_key = True),
    Column('id_of_item',Integer),
    Column('id_of_list',Integer),
    Column('qty_of_item',Integer)
    )