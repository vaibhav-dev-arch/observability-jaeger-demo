# Spring Boot Jaeger Demo

This project demonstrates distributed tracing in a Spring Boot application using Jaeger. It shows how to implement and configure distributed tracing to monitor and troubleshoot microservices-based applications.

## Prerequisites

- Java 11 or higher
- Maven
- Docker
- Docker Compose (optional)

## Technology Stack

- Spring Boot
- OpenTelemetry
- Jaeger
- Maven

## Project Structure

```
jaeger-demo/
├── src/
│   └── main/
│       ├── java/
│       │   └── org/springframework/demo/jaeger/
│       │       ├── JaegerConfig.java
│       │       ├── JaegerExample.java
│       │       ├── JaegerTelemetryConfiguration.java
│       │       ├── SampleController.java
│       │       └── JaegerDemoApplication.java
│       └── resources/
│           └── application.yml
└── pom.xml
```

## Setup and Running

### 1. Start Jaeger

Run Jaeger using Docker:

```bash
docker run -d --name jaeger \
  -p 5775:5775/udp \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 14250:14250 \
  -p 14268:14268 \
  -p 14269:14269 \
  -p 9411:9411 \
  jaegertracing/all-in-one:1.39
```

### 2. Run the Application

```bash
./mvnw spring-boot:run
```

The application will start on port 8080.

## Configuration

The application is configured via `application.yml`:

```yaml
spring:
  application:
    name: jaeger-demo
  sleuth:
    sampler:
      probability: 1.0  # Sample 100% of traces

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always

server:
  port: 8080

logging:
  level:
    org.springframework.web: INFO
    org.springframework.sleuth: DEBUG
    io.opentelemetry: DEBUG
```

## Accessing Jaeger UI

Access the Jaeger UI at: http://localhost:16686

## Available Endpoints

The application exposes the following REST endpoints:

- `GET /sample`: Generates sample traces by executing the `mySampleUseCase` method three times. Each execution creates a span with events and a simulated workload.
  - Response: Returns a message directing you to check the Jaeger UI
  - Example: `curl http://localhost:8080/sample`

## Viewing Traces

1. Open Jaeger UI at http://localhost:16686
2. Select "jaeger-demo" from the service dropdown
3. Click "Find Traces" to view the traces

## Contributing

Feel free to submit issues and enhancement requests.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
