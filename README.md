# Mancala Game REST API

## Overview

This repository contains a Spring Boot REST API for the classic Mancala game. The application is built with Spring Boot and utilizes MongoDB for the database and Redis for caching game data. The application can be run the locally on your host machine, or as containerized environment using Docker.

## Prerequisites

Before running the Mancala Game API, you should have the following prerequisites installed on your system:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Git (for cloning the repository)
- Ensure that port 8080 is free to use (application runs on this port)

## Getting Started

Follow these steps to get the Mancala Game API up and running:

1. Clone this repository to your local machine using Git:

   ```bash
   git clone https://github.com/your-username/mancala-game-api.git

2. Change your current directory to the root folder of the project:
   ```bash
   cd mancala-game

3. Run the application using Docker Compose

   ```bash
   docker-compose up

This command will build and start the necessary containers for the Mancala Game API, including the Spring Boot application, MongoDB, and Redis.

### Swagger UI
Once the application is up and running, you can access API documentation via Swagger UI at http://localhost:8080/swagger-ui/index.html
You are now ready to interact with the Mancala Game API.

![Alt Text](https://github.com/giftmbanda/mancala-game/blob/main/mancala-api/SwaggerUI_screenshot.png)
![Alt Text](https://github.com/giftmbanda/mancala-game/blob/main/mancala-api/Game_screenshot.png)
![Alt Text](https://github.com/giftmbanda/mancala-game/blob/main/mancala-api/Game_in_progress_screenshot.png)
POST http://localhost:8080/game/create - Create a game and returns game object having id, pits, and player

PUT http://localhost:8080/game/{gameId}/pit/{PitId} - Update/plays on pits of passed pitId in the game of passed gameId
