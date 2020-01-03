FROM openjdk:8
VOLUME /tmp
EXPOSE 8383
ADD ./target/microService-SavingAccount-0.0.1-SNAPSHOT.jar service-savingaccount.jar
ENTRYPOINT ["java","-jar","/service-savingaccount.jar"]