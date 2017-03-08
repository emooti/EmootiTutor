#
FROM ubuntu:14.04
MAINTAINER Uta Kapp "utakapp@gmail.com"
RUN apt-get -y update
RUN apt-get -y install openjdk-7-jdk
ENV REFRESHED_AT 2015-03-22
ENV MAVEN_OPTS="-Xms512m -Xmx4096m -XX:MaxPermSize=1024m"
ENV GIT_DISCOVERY_ACROSS_FILESYSTEM 1
RUN apt-get -y install wget
RUN apt-get -y install curl vim git maven 
RUN mkdir /home/emootitutor
RUN cd /home/emootitutor
RUN git init
# pull from GitHub
# build Emootibantransformer
RUN git remote add emootitutor https://github.com/emooti/EmootiTutor.git
RUN git pull emootitutor HEAD
RUN ls -a
RUN cd /home/emootitutor/Tutorial2/Emootibantransformer
RUN mvn clean compile install
VOLUME ["/home/emootitutor/EmootiBan"]
WORKDIR /home/emootitutor/EmootiBan
RUN cd /home/emootitutor/EmootiBan
RUN mvn -version
# build war when executing container
ENTRYPOINT ["/usr/share/maven/bin/mvn" "clean" "package"]
CMD []
# start the docker environment afterwards

