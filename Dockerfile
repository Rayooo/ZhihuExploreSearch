FROM hub.c.163.com/library/tomcat:8.0.42-jre8
MAINTAINER Ray 123@qq.com

RUN rm -rf /usr/local/tomcat/webapps/ROOT && \
    mkdir /usr/local/tomcat/webapps/ROOT && \
    chmod 777 /usr/local/tomcat/webapps/ROOT
    
COPY . /usr/local/tomcat/webapps/ROOT

EXPOSE 8080

CMD catalina.sh run