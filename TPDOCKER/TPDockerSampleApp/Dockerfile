FROM ubuntu:16.04 AS ubuntu

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

RUN apt-get update && \
# install require tools
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y maven && \
    apt-get install -f libpng16-16 && \
    apt-get install -f libjasper1 && \
    apt-get install -y libdc1394-22

ADD . /app/
WORKDIR /app
RUN mvn install:install-file -Dfile=./lib/opencv-3410.jar \
     -DgroupId=org.opencv  -DartifactId=opencv -Dversion=3.4.10 -Dpackaging=jar && \ 
    mvn package
EXPOSE 8080
CMD java -Djava.library.path=lib/ -jar target/fatjar-0.0.1-SNAPSHOT.jar
