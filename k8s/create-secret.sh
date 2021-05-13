#!/bin/bash
kubectl delete secret leocloudcredentials
kubectl create secret generic leocloudcredentials \
    --from-file=.dockerconfigjson=$HOME/.docker/config.json \
    --type=kubernetes.io/dockerconfigjson