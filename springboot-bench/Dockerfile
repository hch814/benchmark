FROM registry.cn-shanghai.aliyuncs.com/kejun/java:210718
USER admin
WORKDIR /home/app
ADD target/springboot-bench.jar .
ADD bin/entrypoint.sh .
RUN chmod a+x entrypoint.sh
ENTRYPOINT ["/home/app/entrypoint.sh"]