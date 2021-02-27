SHELL=/bin/bash

all:
	docker-compose up --build

build: 
	docker-compose build

stop:
	docker-compose down

clean:
	$(MAKE) stop || echo "not running"
	docker container prune --force
	docker image prune --force
	docker volume prune --force
	echo "rmi..."
	docker rmi -f $$(docker images -q)

deploy:
	$(MAKE) build
	docker tag mysql:8 registry.l.aberger.at/caberger/mysql:8
	docker push registry.l.aberger.at/caberger/mysql:8

dropdb:
	$(MAKE) stop
	docker volume rm docker_database

.PHONY: cleanall all stop dropdb deploy
