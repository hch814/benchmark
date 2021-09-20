#!/bin/bash

JAR_NAME="${APP_NAME}.jar"
mkdir jvm
VM_OPTIONS="-javaagent:/skywalking/agent/skywalking-agent.jar
-Dskywalking.agent.service_name=${APP_NAME}
-Dskywalking.collector.backend_service=skywalking-oap.share:11800
-XX:InitialRAMPercentage=75.0
-XX:MinRAMPercentage=75.0
-XX:MaxRAMPercentage=75.0
-Xloggc:$(pwd)/jvm/gc.log
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=$(pwd)/jvm/java.hprof"
PROGRAM_ARGS=""

#在$*中遍历参数，此时每个参数都是独立的，会遍历$#次
for arg in $*; do
  if [[ $arg == --* ]]; then
      PROGRAM_ARGS="$PROGRAM_ARGS "$arg
  else
      VM_OPTIONS="$VM_OPTIONS "$arg
  fi
done

java $VM_OPTIONS -jar $JAR_NAME $PROGRAM_ARGS