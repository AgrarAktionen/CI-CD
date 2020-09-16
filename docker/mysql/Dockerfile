FROM mysql:8

RUN chown -R mysql:mysql /docker-entrypoint-initdb.d/
RUN mkdir -p /tmp/sql
COPY *.sql *.sh /tmp/sql/
RUN \
    ls -l /tmp/sql && \
    cp /tmp/sql/ddl.sql /docker-entrypoint-initdb.d/setup.sql && \
    cp /tmp/sql/*.sh /docker-entrypoint-initdb.d/ && chmod +x /docker-entrypoint-initdb.d/*.sh &&\
    cat /tmp/sql/data.sql >> /docker-entrypoint-initdb.d/setup.sql && rm -rf /tmp/sql/ && \
    cat /docker-entrypoint-initdb.d/setup.sql && \
    rm -rf /tmp/sql

#RUN echo "ALTER USER 'user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';"|mysql -uroot --database=mysql --password=password="${MYSQL_ROOT_PASSWORD}"
