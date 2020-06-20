# defines a source container image to build upon
FROM openjdk:8-jre-alpine

# add metadata to an image
LABEL maintainer="Robert Gomez <test@gmail.com>"

# adding a volume to save the logs
VOLUME /tmp

# copy a local file into the container
COPY build/libs/PracticaDeDockerService-0.0.1-SNAPSHOT.jar /app.jar

# environment variables
ENV JAVA_OPTS=""

# default if no custom port is specified by command line
ENV PORT=8080

# the app will listen on port ####
EXPOSE ${PORT}

# tells Docker what it should execute when you run that container
ENTRYPOINT ["sh", "-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
