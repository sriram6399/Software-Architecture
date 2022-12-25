from sqlalchemy import create_engine, MetaData

engine = create_engine("mysql+pymysql://root:rootPassword@sa-project.cotekv4ums9w.us-east-1.rds.amazonaws.com:3306/sa_project")
meta = MetaData()
conn = engine.connect()
