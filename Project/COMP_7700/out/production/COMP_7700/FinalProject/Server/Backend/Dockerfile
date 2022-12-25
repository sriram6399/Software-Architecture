FROM amazoncorretto:latest

EXPOSE 23987

RUN yum update -y && yum install -y ant

WORKDIR /opt/final_project

COPY . .

RUN ant server && ant clean-build && rm -r src/ build.xml

CMD ["./run_server.sh"]

