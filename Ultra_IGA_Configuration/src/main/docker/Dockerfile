FROM iga-base-image
VOLUME /tmp
ADD ultra-iga-configuration-1.0.jar app.jar
ADD local_policy.jar /opt/jdk/jre/lib/security/local_policy.jar
ADD US_export_policy.jar /opt/jdk/jre/lib/security/US_export_policy.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.awt.headless=true -XX:+UseConcMarkSweepGC -Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
