FROM debian:bullseye

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update && apt-get install -y locales
 
## Set LOCALE to UTF8
#
RUN echo "en_US.UTF-8 UTF-8" > /etc/locale.gen && \
    locale-gen en_US.UTF-8 && \
    dpkg-reconfigure locales && \
    /usr/sbin/update-locale LANG=en_US.UTF-8
ENV LC_ALL en_US.UTF-8

RUN apt update && apt install -y \
  subversion \
  ant \
  bash \
  openjdk-11-jdk \
  junit5 \
  less \
  git \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /openindoor
RUN svn co -r 35892 https://josm.openstreetmap.de/osmsvn/applications/editors/josm
# RUN svn export -r 35892 https://josm.openstreetmap.de/osmsvn/applications/editors/josm

WORKDIR /openindoor/josm/core
RUN ant

WORKDIR /openindoor/josm/plugins/openindoor

# RUN ant dist

