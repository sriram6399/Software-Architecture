from pydantic import BaseModel

class List(BaseModel):
    
    title:str
    description:str
    list_type:str
    owner_id:int
    