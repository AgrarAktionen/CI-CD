FROM openjdk:14

RUN mkdir /deployments
COPY ./deployments/*.jar /deployments/
COPY ./deployments/lib/* /deployments/lib/
COPY scripts/* /deployments/
RUN sed -e 's/\r$//' -i /deployments/*.sh && chmod +x /deployments/*.sh

EXPOSE 8080

ENTRYPOINT ["/deployments/run.sh"]