FROM gradle:jdk17

WORKDIR /app

COPY build.gradle ./

COPY src ./src

CMD ["gradle", "bootRun"]
