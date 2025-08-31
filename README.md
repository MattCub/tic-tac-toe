# Tic-Tac-Toe Project

This repository contains a full-stack Tic-Tac-Toe application with a PostgreSQL database, a Spring Boot backend, and a Vite/React frontend. The project is containerized using Docker and Docker Compose.

## Requirements

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)
- (Optional) Java 17+ and Node.js 20+ if you want to run or build backend/frontend outside Docker

## Getting Started

1. **Clone the repository:**

   ```sh
   git clone https://github.com/your-username/your-repo.git
   cd tic-tac-toe
   ```

2. **Environment Variables:**

   - The `.env` file is included in the repository for testing and convenience.
   - **Note:** Normally, `.env` files should not be committed to version control for security reasons. Here, it is included (and `.env` is commented out in `.gitignore`) so you can run the project without extra setup.
   - If deploying to production, remove `.env` from the repo and add it to `.gitignore`.

3. **Start the application:**
   ```sh
   docker-compose up --build
   ```
   - This will start the PostgreSQL database, backend, and frontend containers.
   - The backend will be available at [http://localhost:8080](http://localhost:8080)
   - The frontend will be available at [http://localhost:5173](http://localhost:5173)

## Project Structure

- `backend-tictactoe/` - Spring Boot backend (Java 17, Maven)
- `frontend-tictactoe/` - Frontend (Vite, Node.js)
- `.env` - Environment variables for database and backend
- `docker-compose.yml` - Multi-container orchestration
- `Dockerfile` - Docker build files for backend and frontend

## Notes

- Database data is persisted in a Docker volume (`db_data`).
- For development, you can modify the code and restart the containers as needed.
