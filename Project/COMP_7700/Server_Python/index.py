from fastapi import FastAPI
from controllers.index import user,user_list,item,list,item_list

app = FastAPI()

@app.get("/")
def open_screen():
    return "Hello Welcome to Backend Server"


app.include_router(user)
app.include_router(item)
app.include_router(item_list)
app.include_router(list)
app.include_router(user_list)