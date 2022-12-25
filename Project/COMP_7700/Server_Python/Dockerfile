FROM amazoncorretto:latest

EXPOSE 8000

RUN yum update -y && yum install -y python3

RUN pip3 install uvicorn fastapi pydantic sqlalchemy pymysql cryptography

WORKDIR /opt/final_project

COPY . .

CMD ["./run.sh"]
