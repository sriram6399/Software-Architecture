from sqlalchemy import create_engine, MetaData

engine = create_engine("mysql+pymysql://root:Passw0rd1!@database:3306/sa_project")
meta = MetaData()
conn = engine.connect()
