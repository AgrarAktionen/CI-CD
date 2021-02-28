#!/bin/bash

kubectl delete -n demo secret leocloudcredentials
kubectl create secret generic leocloudcredentials \
    --namespace=demo \
    --from-file=.dockerconfigjson=/home/aberger/.docker/config.json \
    --type=kubernetes.io/dockerconfigjson