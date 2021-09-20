FROM registry.cn-shanghai.aliyuncs.com/kejun/java
ARG APP_NAME="springboot-bench"
ENV APP_NAME=${APP_NAME}
WORKDIR /home/app/${APP_NAME}
ADD target/${APP_NAME}.jar .
ADD bin/* .
RUN chown -R app:app . && chmod +x entrypoint.sh
USER app
ENTRYPOINT ["./entrypoint.sh"]