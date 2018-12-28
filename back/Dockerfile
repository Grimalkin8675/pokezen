# pokezen-back:latest
FROM hseeberger/scala-sbt:11.0.1_2.12.8_1.2.7

EXPOSE 9000

ENV BASEDIR=/scalapi

RUN mkdir $BASEDIR
WORKDIR $BASEDIR
COPY . .
RUN sbt clean stage

ENTRYPOINT target/universal/stage/bin/pokezen
