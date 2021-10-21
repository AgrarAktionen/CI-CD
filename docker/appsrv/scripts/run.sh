#!/bin/bash

RUNNER=$(ls *-runner.jar)

while ! nc -z mysql 3306; do   
    echo "waiting for godot..."
    sleep 1
done

echo "starting quarkus $RUNNER..."
java -Dquarkus.profile=prod -jar $RUNNER
