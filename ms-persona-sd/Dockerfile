FROM deisylh/ubuntu-basic

RUN mkdir -p /opt/miguel

WORKDIR /opt/miguel

COPY . ./

RUN mvn clean install

EXPOSE 8080

RUN chmod a+x ./entrypoint.sh
RUN chown $USER:$USER ./entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]