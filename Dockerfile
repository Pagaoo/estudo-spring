FROM openjdk:22-jdk-slim
COPY target/*.jar deliciasdovovo.jar
ENTRYPOINT ["java","-jar","/deliciasdovovo.jar"]