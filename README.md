<div align="center">

# Aura

The only discord bot you will need
</div>

## Technical Stuff

### Compiling

#### Prerequisites

- A JDK (JDK 15 is recommended)

### Running

#### Prerequisites

- PSQL (PostgreSQL) database
- Discord Bot Token
- A JRE (JRE 15 is recommended)

#### Env file variables

```properties
BOT_TOKEN=YOUR_DISCORD_BOT_TOKEN
DATABASE_URL=jdbc:postgresql://localhost:5432/aura
DATABASE_USER=postgres
DATABASE_PASSWORD=YOUR_POSTGRES_PASSWORD
```

#### Additional

[Exposed](https://github.com/Jetbrains/Exposed) should generate all the tables on startup, so you don't have to worry
about that :)

### Developing

#### Prerequisites

- A JDK (JDK 15 is recommended)
- An Integrated Development Environment (IDE) ([IntelliJ IDEA](https://www.jetbrains.com/idea/) is recommended)
- PSQL (PostgreSQL) database

#### Recommendations

For your own sanity we recommend running the database in a [Docker](https://www.docker.com/) container.

Checkout the official [Postgres Docker Image](https://hub.docker.com/_/postgres)