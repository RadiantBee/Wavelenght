FROM openjdk:18

COPY ./build/libs/Wavelenght-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT java -jar /app/Wavelenght-0.0.1-SNAPSHOT.jar

EXPOSE 8082
