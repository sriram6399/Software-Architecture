from pydantic import BaseModel

class Item(BaseModel):
    
    title:str
    description:str
    price:float
    qty_avail:int
    qty_sold:int
    seller_id:int