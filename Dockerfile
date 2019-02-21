FROM openjdk:8-jdk-alpine
VOLUME /tmp
# 替换aliyun镜像，设置时区
RUN echo "https://mirrors.aliyun.com/alpine/latest-stable/main" > /etc/apk/repositories \
&& echo "https://mirrors.aliyun.com/alpine/latest-stable/community" >> /etc/apk/repositories \
&& apk update \
&& apk add --no-cache tzdata \
&& cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo "Asia/Shanghai" > /etc/timezone \
&& apk del tzdata
ADD target/ebpay.jar app.jar
ADD conf /conf
ENV JAVA_OPTS=""
EXPOSE 8092
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar

