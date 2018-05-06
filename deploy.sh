#!/bin/bash
# A sample Bash script, by Ryan

rm /var/lib/tomcat8/webapps/cinema.war
rm -rf /var/lib/tomcat8/webapps/cinema

cp /home/ivan/WebDev/Progetto_WEB_2018/cinema/target/cinema-1.0-SNAPSHOT.war /var/lib/tomcat8/webapps/cinema.war
cp -r /home/ivan/WebDev/Progetto_WEB_2018/cinema/target/cinema-1.0-SNAPSHOT.war /var/lib/tomcat8/webapps/cinema

ls /var/lib/tomcat8/webapps
