FROM registry.cn-shanghai.aliyuncs.com/kejun/java
ARG APP_NAME="springboot-bench"
ENV APP_NAME=${APP_NAME}
WORKDIR /home/app/${APP_NAME}
ADD target/${APP_NAME}.jar .
ADD bin/* .
RUN chown -R app:app /home/app && chmod -R a+rw /skywalking && chmod +x entrypoint.sh
USER app
ENTRYPOINT ["./entrypoint.sh"]