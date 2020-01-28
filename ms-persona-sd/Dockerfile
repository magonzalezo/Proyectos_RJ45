FROM deisylh/ubuntu-basic

RUN mkdir -p /opt/miguel

WORKDIR /opt/miguel

COPY . ./

RUN mvn clean install

EXPOSE 8080

CMD java -jar ./target/personas-0.0.1-SNAPSHOT.jar
