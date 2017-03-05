# Dockerfile
# GitHub and do a maven build it now
#
FROM emooti/tutorbase
MAINTAINER Uta Kapp "uta.kapp@emooti.org"
RUN apt-get -y update
#
ENV REFRESHED_AT 2017-03-03
ENV MAVEN_OPTS="-Xms512m -Xmx2048m -XX:MaxPermSize=512m"
ENV GIT_DISCOVERY_ACROSS_FILESYSTEM 1
RUN mkdir /emootitutor
RUN cd /emootitutor && pwd && git init && ls
RUN git init
# pull from GitHub
# build Emootibantransformer
RUN cd /emootitutor && git remote add emootitutor https://github.com/emooti/EmootiTutor.git && git pull emootitutor HEAD
RUN cd /emootitutor/Tutorial3/EmootiBan && mvn clean compile install
VOLUME ["/home/emootitutor/Tuiorial3/Emooti"]
WORKDIR /home/emootitutor/Tutorial3/Emooti
RUN cd /emootitutor/Tutorial3/Emooti && mvn clean package
