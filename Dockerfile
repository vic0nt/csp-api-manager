FROM        artifactory.raiffeisen.ru/csp-docker/adoptopenjdk/maven-openjdk11-signed
ARG         ARTI_LOGIN
ARG         ARTI_PWD
RUN         mkdir /csp-api-manager $HOME/.m2
COPY        . /csp-api-manager
WORKDIR     /csp-api-manager
RUN         sed -i.bak s/Srv_login/$ARTI_LOGIN/g .m2/settings.xml && \
            sed -i.bak s/Der_Parol/$ARTI_PWD/g .m2/settings.xml && \
            mvn -s .m2/settings.xml package
FROM        artifactory.raiffeisen.ru/csp-docker/adoptopenjdk/maven-openjdk11-signed
WORKDIR     /csp-api-manager
COPY        --from=0 /csp-api-manager/target/*.jar csp-api-manager.jar
ENTRYPOINT ["java","-jar","csp-api-manager.jar"]
