FROM openjdk:19-jdk-slim
MAINTAINER qy<2186256471@qq.com> #定义作者信息
ADD ./docker-dashboard-server/target/docker-dashboard-server-0.0.1-SNAPSHOT.jar  APP.jar
EXPOSE 8080
CMD java -jar APP.jar




