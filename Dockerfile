FROM openjdk:13-jdk

ARG JAR_FILE=activity-app/build/libs/*.jar
COPY ${JAR_FILE} app.jar

#set default profile to test
ARG PROFILE="dev-remote"
ENV SPRING_PROFILE $PROFILE
RUN echo ${SPRING_PROFILE}

CMD [ "sh", "-c", "java -Dspring.profiles.active=${SPRING_PROFILE} -Dserver.port=${PORT} -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
