#!/bin/bash
NAMESPACE=default
kubectl delete -n $NAMESPACE secret leocloudcredentials
kubectl create secret generic leocloudcredentials \
    --namespace=$NAMESPACE \
    --from-file=.dockerconfigjson=$HOME/.docker/config.json \
    --type=kubernetes.io/dockerconfigjson