FROM alpine as actions
ADD ./actions/Aura.tar /src/bot/

FROM openjdk:16-alpine
WORKDIR /src/bot
COPY --from=actions /src/bot/Aura-* /src/bot

RUN apk add xvfb firefox ttf-dejavu
RUN export DISPLAY=:99
RUN Xvfb :99 -ac &

ENTRYPOINT [ "sh", "/src/bot/bin/Aura" ]