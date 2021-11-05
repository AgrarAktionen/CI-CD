#!/bin/bash

RUNNER=$(ls *-runner.jar)

echo "starting quarkus $RUNNER..."
tail -f /dev/null
java -Dquarkus.profile=prod -jar $RUNNER
