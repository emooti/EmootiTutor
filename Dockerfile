# Dockerfile Tutorial1
# GitHub and do a maven build it
FROM emooti/tutorbase
MAINTAINER Uta Kapp "uta.kapp@emooti.org"
RUN apt-get -y update

ENV REFRESHED_AT 2016-02-29
ENV MAVEN_OPTS="-Xms512m -Xmx2048m -XX:MaxPermSize=512m"
ENV GIT_DISCOVERY_ACROSS_FILESYSTEM 1
RUN mkdir /emootitutor
RUN cd /emootitutor && pwd && git init && ls
RUN cd /emootitutor && git remote
#pull from GitHub
# build Emootibantransformer
RUN cd /emootitutor && git remote add emootitutor https://github.com/emooti/EmootiTutor.git
RUN cd /emootitutor && git pull emootitutor HEAD
RUN cd /emootitutor/Tutorial1/HelloEmooti && mvn clean compile install
VOLUME ["/emootitutor/Tutorial1/Sprint1"]
WORKDIR /emootitutor/Tutorial1/Sprint1
RUN cd /emootitutor/Tutorial1/Sprint1 && mvn clean package
RUN cd /emootitutor/Tutorial1/Sprint1 && ls
# build war when executing container
#ENTRYPOINT ["/usr/share/maven/bin/mvn" "clean" "package"]
#CMD []
