FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER frank <xhz7085@163.com>
ENV HRC_UPLOAD_PATH /data/hrc/uploads
VOLUME /tmp
ADD hrc-admin-1.0.0-SNAPSHOT.jar /app/app.jar
RUN touch /app/app.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime
EXPOSE 8888:8888
ENTRYPOINT ["java","-Xmx256M","-Duser.timezone='Asia/Shanghai'","-Xms256M","-jar","/app/app.jar"]