FROM adoptopenjdk:jdk

RUN mkdir /deployments
COPY scripts/* /deployments/
RUN sed -e 's/\r$//' -i /deployments/*.sh && chmod +x /deployments/*.sh

EXPOSE 8080
WORKDIR /deployments
ENTRYPOINT ["./run.sh"]