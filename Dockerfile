FROM openjdk:18

COPY ./build/libs/demox-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT java -jar /app/demox-0.0.1-SNAPSHOT.jar

EXPOSE 8082
