FROM node:14

RUN mkdir -p /srv/app/html
COPY package.json /srv/app/
RUN cd /srv/app && npm install && rm -f package-lock.json
COPY dist/main.js html/ /srv/app/

EXPOSE 8080
CMD ["tail", "-f", "/dev/null"]
WORKDIR /srv/app

