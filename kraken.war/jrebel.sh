#!/bin/bash

pserver=/root/java/servers/10.0.0.Final
pproject=/root/java/workspace/kraken/kraken.war

for pfile in $( find $pproject/src/main/webapp/ | grep -i \.xhtml$ )
do
     file=$(basename $pfile)
	
	 for eappfile in $( find $pserver/standalone/ -iname $file )
	 do	
	 
	   cp -vu $pfile $eappfile
	 
	 done		
		
done


for pfile in $( find $pproject/src/main/webapp/css/ | grep -i \.css$ )
do
     file=$(basename $pfile)
	
	 for eappfile in $( find $pserver/standalone/ -iname $file )
	 do		 
	   cp -vu $pfile $eappfile
	 
	 done		
		
done

for pfile in $( find $pproject/src/main/webapp/ | grep -i \.html$ )
do
     file=$(basename $pfile)
	
	 for eappfile in $( find $pserver/standalone/ -iname $file )
	 do		 
	   cp -vu $pfile $eappfile
	 
	 done		
		
done