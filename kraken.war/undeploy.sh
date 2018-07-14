#!/bin/bash

war=kraken.war
pserver=/root/java/servers/10.0.0.Final

rm -rf $pserver/standalone/deployments/$war.deployed
echo $war > $pserver/standalone/deployments/$war.undeployed
rm -rf $pserver/standalone/deployments/$war

rm -rf $pserver/standalone/tmp/$war
rm -rf $pserver/standalone/tmp/vfs/deployment/*
rm -rf $pserver/standalone/tmp/vfs/temp/*