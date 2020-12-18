FROM docker.io/tomcat:latest
VOLUME /tmp
ADD oatest.war /var/lib/docker/containers/bcb329499306d23387fed4e700e42c38698fc9a7c0be8073468cb4910488502a
RUN sh -c 'touch /oatest.war'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /oatest.war" ]
