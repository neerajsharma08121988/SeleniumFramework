FROM maven:3.8.1-jdk-11
WORKDIR /app
COPY . .
RUN mvn clean install
ENTRYPOINT ["mvn", "test"]
