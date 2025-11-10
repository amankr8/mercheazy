# MerchEazy

MerchEazy is a sample microservices-based merchandise storefront and administration platform. This repository contains multiple Spring Boot backend services, an Angular frontend, and dev infrastructure (Docker Compose and Kubernetes manifests) so you can run the full system locally for development or demo purposes.

This README summarizes the repository layout, how to run the application (Docker Compose and individual services), and useful development tips discovered in the project files.

Quick links
- Frontend: `./frontend`
- Backend services: `./backend` (contains `api-gateway`, `service-registry`, `auth-service`, `user-service`, `product-service`, `order-service`)
- Dev infra: `docker-compose.yml`, `k8s/` manifests

Project overview
- Microservices architecture built with Spring Boot (Java 21) and Spring Cloud (Eureka service registry, Spring Cloud Gateway).
- Angular frontend (Angular 19) served from `./frontend`.
- Services communicate via REST, and the project includes Kafka, PostgreSQL, Redis and Elasticsearch in the Docker Compose profile for eventing, persistence and search.

Repository layout (important files)
- docker-compose.yml — development compose file that brings up the frontend, Postgres, Kafka (and Zookeeper), Elasticsearch, Redis and backend services.
- frontend/ — Angular app and Dockerfile to build a static site served by nginx.
- backend/* — each microservice is a standard Spring Boot Maven project with a `Dockerfile` that builds a runnable jar.
- k8s/ — Kubernetes manifests and an `ingress` example for deploying to a cluster.
- backend/*/HELP.md — service-specific notes (auto-generated) with references to Spring/GitHub docs.

Ports used by docker-compose (defaults)
- Frontend: 4200 (container exposes 80; compose maps host:4200 -> container:80)
- Service Registry (Eureka): 8761
- API Gateway: 8080
- Auth Service: 8081
- User Service: 8082
- Product Service: 8084
- Order Service: 8085
- PostgreSQL: 5432
- Kafka: 9092
- Zookeeper: 2181
- Redis: 6379
- Elasticsearch: 9200

Prerequisites
- Docker Engine and Docker Compose v1.29+ (or Docker Desktop) installed locally
- Java 21 (if you want to build/run services locally without Docker)
- Maven (optional - the projects include `mvnw` wrappers)
- Node 20 / npm (for frontend development)

Environment variables
- JWT_SECRET — required by `auth-service` when running via Docker Compose. Set this in your shell before `docker compose up` or provide it via an `.env` file. Example:

  export JWT_SECRET=replace-with-a-secret

- Database credentials used by the compose file (these are the defaults in docker-compose.yml):
  - POSTGRES_USER=postgres
  - POSTGRES_PASSWORD=postgres
  - POSTGRES_DB=mercheazy

How to run (recommended: Docker Compose)
1. From the repository root, ensure Docker is running.
2. (Optional) Set JWT secret:

```bash
export JWT_SECRET=replace-me
```

3. Start everything:

```bash
docker compose up --build
```

4. When compose finishes, open the frontend at http://localhost:4200 and the Eureka UI at http://localhost:8761. The API gateway listens on http://localhost:8080.

Notes about the compose setup
- The compose file includes Kafka and Zookeeper (Confluent images), Elasticsearch, and Redis. These are intended for local development and demos only.
- The backend service containers are built from the local folders under `./backend` using each service's `Dockerfile` which runs the included `./mvnw` wrapper to build the jar.

Running services individually (development)
- Backend (Java/Spring): Each backend service is a Maven project. To build one service locally you can run from the service folder:

```bash
cd backend/auth-service
./mvnw clean package -DskipTests
java -jar target/*.jar
```

- Frontend (Angular): For development with live reload:

```bash
cd frontend
npm install
npm start
# Open http://localhost:4200 (default Angular dev server port)
```

Kubernetes
- The `k8s/` directory contains manifests (deployments, services, ingress). These are intended as a starting point for deploying to a cluster. You'll need to adapt image references and secrets for production.

Helpful notes from service HELP files
- The backend HELP.md files note the project uses Java 21 and Spring Boot 3.5.x; they also point to Spring Cloud (Eureka, Gateway, OpenFeign) references.
- Some service packages were auto-corrected (package names in generated code may use underscores). See each `backend/*/HELP.md` for links and notes.

Common tasks and tips
- Rebuilding all backend services without Docker (from the `backend` folder) can be done with Maven wrapper in each service or by scripting a build across projects.
- If you get failures related to service discovery, confirm the service-registry (Eureka) is healthy at http://localhost:8761 and that services point to the correct eureka URL.
- If Elasticsearch fails to start due to memory constraints, adjust `ES_JAVA_OPTS` or run it separately with more memory on your machine.

Security & production notes
- The docker-compose file exposes many services and uses default credentials — DO NOT use this setup in production.
- Replace JWT and DB passwords with strong secrets, and secure service-to-service communication (mTLS, network policies) in production deployments.

Testing
- Backend: Each service includes a `src/test` directory — run `./mvnw test` inside the service folder.
- Frontend: Run `npm test` inside `./frontend`.

Troubleshooting
- Docker build errors: check the service's `Dockerfile` and ensure the `mvnw` wrapper is executable (if on macOS/Linux run `chmod +x mvnw`).
- Ports conflicts: Ensure no other services are using the mapped host ports (4200, 8080, 8761, 5432, etc.).

Next steps / improvements (suggested)
- Add a top-level script to build all backend services and the frontend in sequence.
- Add health checks and readiness probes for Kubernetes manifests.
- Parameterize Docker Compose with an `.env` file and add a `docker-compose.override.yml` for local dev overrides.
- Add a CONTRIBUTING.md and a short architecture diagram (PlantUML or Mermaid) to describe service interactions.

Authors / Maintainers
- Repository scaffolding and service samples appear to be generated by project tooling; update this section with actual maintainers and contact points.

License
- No license was found in the repository. Add a LICENSE file (for example MIT) if you intend to open source this project.

---

Notes
- This README is a template based on the repository name and common project patterns. Update commands, env names, services, and structure to accurately reflect the codebase.
