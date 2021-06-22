FROM node:14

RUN mkdir -p /srv/app/html
COPY package.json /srv/app/
RUN cd /srv/app && npm install && rm -f package-lock.json
COPY dist/main.js /srv/app/
COPY config /srv/app/config/

EXPOSE 8080
#CMD ["tail", "-f", "/dev/null"]
CMD ["node", "main.js", "--config", "config/cloud-settings.yaml"]
WORKDIR /srv/app

