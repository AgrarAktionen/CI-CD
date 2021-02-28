FROM nginx:stable

COPY default.conf /etc/nginx/conf.d/default.conf
COPY html/* /usr/share/nginx/html/
WORKDIR /usr/share/nginx/html
EXPOSE 80
