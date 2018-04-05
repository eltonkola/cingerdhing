FROM ubuntu:16.04
LABEL MAINTAINER Elton Kola <eltonkola@gmail.com> <https://eltonkola.com/)


VOLUME ["/radio_media"]

USER root
RUN apt-get -y update

RUN mkdir -p /var/cingerdhing
RUN chmod 0777 -R /var/cingerdhing



# ----------------------------------------------------------------------------
# original from: https://github.com/mlaccetti/docker-oracle-java8-ubuntu-16.04
# ----------------------------------------------------------------------------

ENV DEBIAN_FRONTEND noninteractive
ENV JAVA_HOME       /usr/lib/jvm/java-8-oracle
ENV LANG            en_US.UTF-8
ENV LC_ALL          en_US.UTF-8

RUN apt-get update && \
  apt-get install -y --no-install-recommends locales && \
  locale-gen en_US.UTF-8 && \
  apt-get dist-upgrade -y && \
  apt-get --purge remove openjdk* && \
  echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections && \
  echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" > /etc/apt/sources.list.d/webupd8team-java-trusty.list && \
  apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EEA14886 && \
  apt-get update && \
  apt-get install -y --no-install-recommends oracle-java8-installer oracle-java8-set-default && \
  apt-get clean all


# ----------------------------------------------------------------------------
# copy spring boot fat jar application
# ----------------------------------------------------------------------------

COPY cingerdhing.jar /var/cingerdhing/cingerdhing.jar
RUN sh -c 'touch /var/cingerdhing/cingerdhing.jar'
ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]


# ----------------------------------------------------------------------------
# install icecast
# ----------------------------------------------------------------------------
RUN apt-get install icecast2 sudo -y
COPY icecast2 /etc/default/icecast2
COPY icecast.xml /usr/share/icecast2/icecast.xml


# ----------------------------------------------------------------------------
# install liquidsoap
# ----------------------------------------------------------------------------
USER root
#copy webradio related stuff
RUN apt-get install -y --no-install-recommends liquidsoap liquidsoap-plugin-all
RUN apt-get install -y festival

RUN echo "liquidsoap ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers


USER liquidsoap

#configuration
COPY run.sh /var/cingerdhing
COPY config.liq /var/cingerdhing
COPY radio.liq /var/cingerdhing

# ----------------------------------------------------------------------------
# start everything
# ----------------------------------------------------------------------------

#USER root
CMD ["/var/cingerdhing/run.sh"]