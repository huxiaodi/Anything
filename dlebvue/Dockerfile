FROM nginx:stable-alpine
COPY nginx/nginx.conf /etc/nginx/nginx.conf
COPY nginx/nginx.vh.default.conf /etc/nginx/conf.d/default.conf
ADD dist/ /usr/share/nginx/html/
# 替换aliyun镜像，设置时区
RUN echo "https://mirrors.aliyun.com/alpine/latest-stable/main" > /etc/apk/repositories \
&& echo "https://mirrors.aliyun.com/alpine/latest-stable/community" >> /etc/apk/repositories \
&& apk update \
&& apk add --no-cache tzdata \
&& cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo "Asia/Shanghai" > /etc/timezone \
&& apk del tzdata
