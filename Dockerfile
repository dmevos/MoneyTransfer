FROM openjdk:11
COPY target/MoneyTransfer-0.0.1-SNAPSHOT.jar TransMoney.jar
EXPOSE 5500
ENTRYPOINT ["java","-jar","TransMoney.jar"]
