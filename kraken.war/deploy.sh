#!/bin/bash

pj=kraken-1.0
war=$pj.war
pserver=/root/java/servers/10.0.0.Final
pproject=/root/java/workspace/Kraken

mkdir -p $pserver/standalone/deployments/$war/WEB-INF/

cp -vur $pproject/target/classes/ $pserver/standalone/deployments/$war/WEB-INF/

cp -vur $pproject/target/m2e-wtp/web-resources/ $pserver/standalone/deployments/$war/

cp -vur $pproject/src/main/webapp/* $pserver/standalone/deployments/$war/

cp -vur $pproject/target/$pj/WEB-INF/lib $pserver/standalone/deployments/$war/WEB-INF/

rm -rf $pserver/standalone/deployments/$war.deployed

rm -rf $pserver/standalone/deployments/$war.undeployed

echo "$war" > $pserver/standalone/deployments/$war.dodeploy

