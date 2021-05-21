#!/bin/bash
kubectl delete secret leocloudcredentials
#kubectl create secret generic leocloudcredentials \
#    --from-file=.dockerconfigjson=$HOME/.docker/config.json \
#    --type=kubernetes.io/dockerconfigjson

if [[ "$2." == "." ]]
then
    echo "usage: $0 <username> <password"
    exit 1
fi

DOMAIN=htl-leonding.ac.at
kubectl create secret docker-registry leocloudcredentials --docker-server=registry.cloud.htl-leonding.ac.at --docker-username=$1@$DOMAIN --docker-password=$2 --docker-email=$1@$DOMAIN

