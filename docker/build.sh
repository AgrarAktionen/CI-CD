#!/usr/bin/env bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
USERNAME=$1

pushd $SCRIPT_DIR
docker build appsrv --tag registry.cloud.htl-leonding.ac.at/${USERNAME}/appsrv || exit 1
docker build express --tag registry.cloud.htl-leonding.ac.at/${USERNAME}/express || exit 2
docker build nginx --tag registry.cloud.htl-leonding.ac.at/${USERNAME}/nginx || exit 3
popd
