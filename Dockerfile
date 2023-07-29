FROM openjdk:17
# copy the source tree and the pom.xml to our new container
ADD /target/CatalogInfoBE-0.0.1-SNAPSHOT.jar backend.jar
# set the startup command to execute the jar
EXPOSE 8081

CMD ["java", "-jar", "backend.jar"]