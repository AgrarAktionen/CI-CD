FROM mysql:8

COPY init/* /docker-entrypoint-initdb.d/
RUN chmod +x /docker-entrypoint-initdb.d/*.sh

#RUN chown -R mysql:mysql /docker-entrypoint-initdb.d/
#RUN mkdir -p /tmp/sql
#COPY *.sql *.sh /tmp/sql/
#RUN \
#    ls -l /tmp/sql && \
#    cp /tmp/sql/ddl.sql /docker-entrypoint-initdb.d/setup.sql && \
#    cp /tmp/sql/*.sh /docker-entrypoint-initdb.d/ && chmod +x /docker-entrypoint-initdb.d/*.sh &&\
#    cat /tmp/sql/data.sql >> /docker-entrypoint-initdb.d/setup.sql && rm -rf /tmp/sql/ && \
#    cat /docker-entrypoint-initdb.d/setup.sql && \
#    rm -rf /tmp/sql

