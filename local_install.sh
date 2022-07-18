#!/bin/bash

# enter Spiral Knights game folder location here
SPIRAL_KNIGHTS_LOCATION="path/to/Spiral Knights"


############ generating local maven repositories ############
CODE_LOCATION="$SPIRAL_KNIGHTS_LOCATION/code"

mkdir ./third_party

mvn deploy:deploy-file -Durl=file:./third_party/ -Dfile="$CODE_LOCATION/projectx-pcode.jar" -DgroupId=com.threerings -DartifactId=projectx -Dpackaging=jar -Dversion=1.0
mvn deploy:deploy-file -Durl=file:./third_party/ -Dfile="$CODE_LOCATION/lwjgl.jar" -DgroupId=org.lwjgl -DartifactId=lwjgl -Dpackaging=jar -Dversion=1.0
