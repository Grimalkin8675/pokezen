# pokezen-front:latest
FROM node:10.15.0

EXPOSE 8080

ENV BASEDIR=/reactfront

RUN mkdir $BASEDIR
WORKDIR $BASEDIR
COPY . .
RUN npm i --production
RUN npm run clean
RUN npm run build
RUN npm install http-server -g

ENTRYPOINT http-server dist
