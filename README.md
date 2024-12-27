# High-Performance Application with Kafka, Redis, and PostgreSQL

This application is designed to handle high traffic efficiently by integrating Kafka for message queuing and Redis for caching. The goal is to optimize performance and scalability by reducing the load on the primary PostgreSQL database.

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [Performance Improvements](#performance-improvements)
- [Usage](#usage)
- [Contributing](#contributing)

## Overview

This application demonstrates an optimized approach to handle a high volume of concurrent requests using:
- **Kafka**: For handling high-throughput data streams.
- **Redis**: For caching frequently accessed data.
- **PostgreSQL**: As the primary database.

## Technologies Used

- **Spring Boot**: For application development.
- **Kafka**: For message queuing.
- **Redis**: For caching.
- **PostgreSQL**: For data storage.
- **Docker**: For containerization.
- **Python**: For simulating concurrent requests.

## Setup and Installation

### Prerequisites

- Docker and Docker Compose installed on your machine.
- Java JDK 21 or higher.
- Python 3.8 or higher.
- Git installed for Git Bash.

### Clone the Repository

```bash
git clone https://github.com/yuvraj0028/kafka-spring-implementation.git
cd kafka-spring-implementation
```

### Configuration
**Docker Compose Configuration**

Add your **Postgres** *user* and *password* in `docker-compose.yaml`

```yaml
  postgres:
    image: postgres:latest
    environment:
      POSTGRES_USER: <YOUR_USERNAME>
      POSTGRES_PASSWORD: <YOUR_PASSWORD>
    ports:
      - 5432:5432
    volumes:
      - pgdata:/var/lib/postgresql/data
```

**Application Properties Configuration for Postgres**

Configure the application properties in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/
spring.datasource.username=<YOUR_USERNAME>
spring.datasource.password=<YOUR_PASSWORD>
```

## Running the Application

### Start Docker Services and Application

Use the `application_run.sh` script to make sure Docker images are up and start the application.

```bash
./application_run.sh
```

### Run the Load Test

```bash
./load_test_run.sh
```

## Performance Improvements
- **Before Kafka Integration**: ~105 seconds to make 180,000,000 write calls to PostgreSQL (~3000 calls per second).
- **After Kafka Integration**: ~55 seconds for the same number of requests.
- **Speed Increase**: Achieved a 90.91% speedup!


## Usage
- **GET /all**: Retrieves all data. First checks `Redis` cache before querying the database.
- **POST /products/add**: Adds new products to the database via `Kafka`.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request for any improvements.
