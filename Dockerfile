FROM alpine as actions
ADD ./actions/Aura.tar /src/bot/

RUN apk install firefox

FROM amd64/openjdk:16
WORKDIR /src/bot
COPY --from=actions /src/bot/Aura-* /src/bot
ENTRYPOINT [ "sh", "/src/bot/bin/Aura" ]