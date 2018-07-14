#!/bin/bash
export JAVA_HOME=/root/Programas/JVMs/jdk1.8.0_102

pproject=/root/java/workspace/Kraken

mvn package -f $pproject/pom.xml
$pproject/deploy.sh
