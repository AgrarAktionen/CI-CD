#!/bin/bash

RUNNER=$(ls application-server-*-runner.jar)

echo "starting quarkus $RUNNER..."
java -Dquarkus.profile=docker -jar $RUNNER
