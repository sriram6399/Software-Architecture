from pydantic import BaseModel

class UserList(BaseModel):
   
    for_sale:int
    my_cart:int
    past_purchases:int
    recommended_items:int
    user_id:int