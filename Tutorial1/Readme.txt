Tutorial1 - EmootiTutor

1. Setup a MuleSoft with Maven
2. Setup a .war for Tomcat
3. Start a first simple application on MuleSoft integration platform
4. build war 
a) cd HelloEmooti
   mvn clean compile install
b) cd EmootiBan
   mvn clean package
c) copy the .war to a Tomcat tomcat.apache.org

Get going with GIT:

git clone https://github.com/emooti/EmootiTutor.git
cd Tutorial1
echo 'Repository EmootiTutor' > README
git add README
git commit -m 'Initial checkin'
git push origin master

Add Remote:

git remote add -f EmootiTutor https://github.com/emooti/EmootiTutor.git
git push EmootiTutor HEAD
