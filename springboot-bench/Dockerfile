FROM registry.cn-shanghai.aliyuncs.com/kejun/java
ARG APP_NAME=springboot-bench
ENV APP_NAME=${APP_NAME}
WORKDIR /home/app
ADD target/${APP_NAME}.jar .
ADD bin/entrypoint.sh .
RUN chown app entrypoint.sh && chmod +x entrypoint.sh
USER app
ENTRYPOINT ["/home/app/entrypoint.sh"]