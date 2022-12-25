FROM amazoncorretto:latest

EXPOSE 5000

RUN yum update -y && yum install -y unzip wget

RUN wget https://dlcdn.apache.org/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.zip && unzip apache-maven-3.2.5-bin.zip && mv apache-maven-3.2.5/ /usr/local/maven/

WORKDIR /opt/final_project

COPY . .

CMD ["./run.sh"]
