#!/bin/sh

export CATALINA_HOME=
export CATALINA_BASE=
export CATALINA_TMPDIR=$CATALINA_BASE/temp
export LOG_PATH=
export LOG_DIR=

export JAVA_OPTS="$JAVA_OPTS -server -Xms1G -Xmx4m -XX:+UseG1GC -XX:+PrintCommandLineFlags -XX:+PrintFlagsFinal"
export JAVA_OPTS="$JAVA_OPTS -Djava.net.preferIPv4Stack=true -Dnetworkaddress.cache.ttl=3600"
export JAVA_OPTS="$JAVA_OPTS -Djava.awt.headless=true -Dcom.sun.management.jmxremote -Dhttps.protocols=TLSv1.2"
export JAVA_OPTS="$JAVA_OPTS -Dhttp.agent=Java-ENT"
export JAVA_OPTS="$JAVA_OPTS -Dserver.webapps= -Dserver.home="