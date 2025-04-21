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

## File Documentation

### 1. JaegerDemoApplication.java
The main Spring Boot application class that serves as the entry point for the application.
- Annotated with `@SpringBootApplication` to enable auto-configuration
- Contains the `main` method that bootstraps the Spring Boot application
- Automatically scans and configures components in the package and sub-packages

### 2. JaegerConfig.java
Configuration class for Jaeger tracing setup.
- Configures the OpenTelemetry SDK
- Sets up the Jaeger exporter
- Defines sampling strategy and trace configuration
- Configures resource attributes for better trace identification

### 3. JaegerTelemetryConfiguration.java
Additional telemetry configuration for the application.
- Sets up OpenTelemetry instance
- Configures trace exporters
- Defines sampling strategy
- Sets up resource attributes for service identification

### 4. JaegerExample.java
Demonstration class showing how to create and use spans in your application.
- Contains `mySampleUseCase()` method that demonstrates span creation
- Shows how to add events to spans
- Demonstrates proper span lifecycle management
- Includes a simulated workload to demonstrate timing in traces

### 5. SampleController.java
REST controller that exposes endpoints for generating traces.
- Exposes a `/sample` endpoint
- Demonstrates how to use the JaegerExample class in a web context
- Shows how to generate multiple traces in a single request
- Returns a message directing users to check the Jaeger UI

### 6. application.yml
Configuration file for the Spring Boot application.
- Sets application name and server port
- Configures Spring Cloud Sleuth sampling rate
- Enables management endpoints
- Sets up logging levels for tracing components

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
