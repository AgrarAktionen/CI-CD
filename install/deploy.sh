#!/bin/bash

DEPLOY_FOLDER=/usr/share/appsrv

rm -rf $DEPLOY_FOLDER
mkdir -p $DEPLOY_FOLDER

cp -r appsrv/target/{application-server-*-runner.jar.original, lib/} $DEPLOY_FOLDER
chown -R quarkus:quarkus $DEPLOY_FOLDER


