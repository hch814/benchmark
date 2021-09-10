FROM registry.cn-shanghai.aliyuncs.com/kejun/java
USER app
ARG APP_NAME=springboot-bench
ENV APP_NAME=${APP_NAME}
WORKDIR /home/app
ADD target/${APP_NAME}.jar .
ADD bin/entrypoint.sh .
RUN chmod a+x entrypoint.sh
ENTRYPOINT ["/home/app/entrypoint.sh"]