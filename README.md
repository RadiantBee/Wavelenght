# Wavelenght

A web application for analysis of satellite telemetry data in a form of signals (multivariate time-series data).

## Tech stack

- **Backend:** Java 25 toolchain, Spring Boot 4, Spring Security, JPA, ActiveMQ
- **Frontend:** React 19 + Vite 6, built by Gradle
- **Build:** Gradle 9

## Build everything with one command

Node.js 18+ and npm must be installed. The Gradle build installs the frontend
dependencies, builds the React app and packages it into the Spring Boot jar:

```bash
./gradlew build
```

The runnable jar is written to `build/libs/Wavelenght-0.0.1-SNAPSHOT.jar`.

Other useful commands:

```bash
./gradlew bootJar      # build backend + frontend jar
./gradlew bootRun      # run the application on http://localhost:8099/wavelenght/
./gradlew npmBuild     # build only the frontend
./gradlew clean build  # clean build of everything
```

## Frontend development

The React sources live in `frontend/`. For hot reload, run the backend and the
Vite dev server in separate terminals:

```bash
./gradlew bootRun
```

```bash
cd frontend
npm install
npm run dev
```

Open <http://localhost:5173>. Vite proxies `/wavelenght` API requests to the
backend on port 8099.

For a production build, `./gradlew build` runs `vite build` and copies the
output into the jar's static resources automatically.
