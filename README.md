<div align="center">

# Aura

The only discord bot you will ever need.
</div>

## Technical Stuff

### Compiling

#### Prerequisites

- A JDK (JDK 16 is recommended)

### Running

#### Prerequisites

- PSQL (PostgreSQL) database
- Discord Bot Token
- A JRE (JRE 16 is recommended)

#### The Docker Compose Way
If you do not have a docker-compose.yml file create one and use the structure below:
```yaml
version: '3.4'

services:
  aura:
    container_name: aura-bot
    image: docker.pkg.github.com/chachydev/aura/aura-master:latest
    restart: always
    env_file:
      - aura.env
  db:
    container_name: aura-database
    image: postgres
    restart: always
    ports:
      - 5432:5432
    env_file:
      - postgres.env
```

Execute `docker compose up -d` and tada 🎉🎉 you have a running instance of Aura :) 

#### Env file variables

```properties
bot_token=YOUR_DISCORD_BOT_TOKEN
database_url=jdbc:postgresql://localhost:5432/aura
database_user=postgres
database_password=YOUR_POSTGRES_PASSWORD
```

#### Additional

[Exposed](https://github.com/Jetbrains/Exposed) should generate all the tables on startup, so you don't have to worry
about that :)

### Developing

#### Prerequisites

- A JDK (JDK 16 is recommended)
- An Integrated Development Environment (IDE) ([IntelliJ IDEA](https://www.jetbrains.com/idea/) is recommended)
- PSQL (PostgreSQL) database

### Recommendations

#### Database

For your own sanity we recommend running the database in a [Docker](https://www.docker.com/) container. Checkout the
official [Postgres Docker Image](https://hub.docker.com/_/postgres)

Example Docker Compose file:

```yaml
version: '3.4'

services:
  db:
    container_name: aura-database
    image: postgres
    restart: always
    ports:
      - 5432:5432
    environment:
      POSTGRES_PASSWORD: YOUR_PASSWORD_HERE
```

If you have docker installed then simply copy this config to a file named `docker-compose.yml` and then
run `docker compose up -d` to start the container

#### Viewing

To view your database contents we recommended you use [DataGrip](https://www.jetbrains.com/datagrip/), which we believe
is an amazing tool to view many databases.