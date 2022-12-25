from pydantic import BaseModel

class ItemList(BaseModel):
    
    id_of_item:int
    id_of_list:int
    qty_of_item:int