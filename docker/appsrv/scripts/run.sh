#!/bin/bash

cd /deployments
java -Dquarkus.profile=docker -jar appsrv-0.5.0-runner.jar
