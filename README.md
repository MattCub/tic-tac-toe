# Tic-Tac-Toe Project

This repository contains a full-stack Tic-Tac-Toe application with a PostgreSQL database, a Spring Boot backend, and a Vite/React frontend. The project is containerized using Docker and Docker Compose.

## Requirements

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)
- (Optional) Java 17+ and Node.js 20+ if you want to run or build backend/frontend outside Docker

## Getting Started

1. **Clone the repository:**

   ```sh
   git clone https://github.com/MattCub/tic-tac-toe.git
   cd tic-tac-toe
   ```

2. **Environment Variables:**

   - The `.env` file is included in the repository for testing and convenience.
   - **Note:** Normally, `.env` files should not be committed to version control for security reasons. Here, it is included (and `.env` is commented out in `.gitignore`) so you can run the project without extra setup.

3. **Start the application:**

   ```sh
   docker-compose up --build
   ```

   - This will build and start the PostgreSQL database, backend, and frontend containers.

   - The backend will be available at [http://localhost:8080](http://localhost:8080).
   - The frontend will be available at [http://localhost:5173](http://localhost:5173)

## Project Structure

- `backend-tictactoe/` - Spring Boot backend (Kotlin, SpringBoot, Java 17, Maven)
- `frontend-tictactoe/` - Frontend (Vite+React)
- `db-init` - Inside are the scripts that creates the needed structure for the database
- `.env` - Environment variables for database and backend
- `docker-compose.yml` - Multi-container orchestration
- `Dockerfile` - Docker build files for backend and frontend

## Project Notes

### Backend

The backend was made following hexagonal architecture, so the inner structure looks like this:

- domain
- api
- application
- infrastructure

### API Endpoints

- `POST /match/create` — Create a new match
- `POST /match/{matchId}/move` — Make a move in a match
- `GET /match/{matchId}/status` — Get the status of a match

You can find more of the API documentation at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

#### Testing

The Backend has unit tests and also integration tests, for the controllers and the repostory.

To run them

```sh
mvn clean install
```

#### Missing features

1. An API declaration to follow API First methodology.

### Frontend

The front was made with Vite for the good and easy starting point it builds for small projects like this one.

The idea was to develop it using "Mobile First" so the application is responsive.

It doesn't have a match history, but you are able to check each played match with only changing the match id in the path. For example: If you want to check the first match you only need to go to [http://localhost:5173/match/1](http://localhost:5173/match/1)

#### Testing

The frontend only has unit tests.

To run them

```sh
npm test
```

#### Missing features

1. Develop proper match history
2. The visual part of the user managemente
