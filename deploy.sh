#!/bin/bash
# A sample Bash script, by Ivan

rm /var/lib/tomcat8/webapps/cinema.war
rm -rf /var/lib/tomcat8/webapps/cinema

cp /home/ivan/WebDev/Progetto_WEB_2018/cinema/target/cinema-1.0-SNAPSHOT.war /var/lib/tomcat8/webapps/cinema.war
cp -r /home/ivan/WebDev/Progetto_WEB_2018/cinema/target/cinema-1.0-SNAPSHOT.war /var/lib/tomcat8/webapps/cinema

. /var/lib/tomcat8/webapps
ls /var/lib/tomcat8/webapps
