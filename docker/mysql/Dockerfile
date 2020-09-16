FROM mysql:5

RUN chown -R mysql:mysql /docker-entrypoint-initdb.d/
RUN mkdir -p /tmp/sql
COPY *.sql /tmp/sql/
RUN \
    ls -l /tmp/sql && \
    cp /tmp/sql/ddl.sql /docker-entrypoint-initdb.d/setup.sql && \
    cat /tmp/sql/data.sql >> /docker-entrypoint-initdb.d/setup.sql && rm -rf /tmp/sql/ && \
    cat /docker-entrypoint-initdb.d/setup.sql && \
    rm -rf /tmp/sql
