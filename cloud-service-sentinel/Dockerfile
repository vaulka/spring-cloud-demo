FROM openjdk:11-jre-stretch
COPY sentinel-dashboard-1.8.1.jar /app/sentinel-dashboard.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", \
"-Xms330M", "-Xmn100M", "-XX:MaxDirectMemorySize=64M", "-XX:MetaspaceSize=100M", \
"-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=GMT+08", \
"-jar", "sentinel-dashboard.jar"]