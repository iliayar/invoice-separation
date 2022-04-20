#!/usr/bin/env bash

cd $(dirname $0)
PACKAGE="ru.itmo.invoiceseparation"

swagger-codegen generate -i ../api/api.yaml -l spring -o ./ --api-package "${PACKAGE}.api" --model-package "${PACKAGE}.model"
