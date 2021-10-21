#!/bin/bash

RUNNER=$(ls *-runner.jar)

while ! nc -z mysql 3306; do   
    echo "waiting for godot..."
    sleep 1
done

echo "starting quarkus $RUNNER..."
tail -f /dev/null
java -Dquarkus.profile=prod -jar $RUNNER
