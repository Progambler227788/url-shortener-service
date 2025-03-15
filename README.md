# URL Shortener API

This is a solution to the URL shortener project on roadmap.sh : https://roadmap.sh/projects/url-shortening-service

## Overview
This is a simple URL Shortener API built using Java and Spring Boot. It allows users to shorten long URLs and retrieve the original URLs when needed.

## Live Deployment
The application is live and can be accessed at:
[Live URL Shortener](https://url-shortener-java-e3f3688791b5.herokuapp.com/)

### API Documentation (Swagger)
[Swagger API Docs](https://url-shortener-java-e3f3688791b5.herokuapp.com/swagger-ui/index.html#/)

## Features
- Shorten a long URL
- Retrieve original URL using the shortened URL
- Update and delete shortened URLs
- Retrieve URL statistics
- Simple and efficient

## Technologies Used
- Java 17
- Spring Boot
- MongoDB
- Swagger for API documentation

## Running Locally
To run the project locally, clone the repository and ensure you have Java, Maven, and MongoDB installed.

### Steps:
1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd url-shortener-java
   ```
2. Set up MongoDB:
   - Make sure MongoDB is running.
   - Update `application.properties` or `application.yml` with your own MongoDB URI.
   - If running locally, use the default port (27017) or specify your own.
3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the API at `http://localhost:8080/` (or your configured port).

## Project Structure
```
- src/main/java/com/example/urlshortener/
  - controller/
    - UrlController.java
    - HomeController.java 
  - service/
  - repository/
  - model/
  - config/
```

## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/shorten` | Shorten a URL |
| `GET` | `/shorten/{shortCode}` | Resolve a short URL |
| `PUT` | `/shorten/{shortCode}` | Update a long URL |
| `DELETE` | `/shorten/{shortCode}` | Delete a short URL |
| `GET` | `/shorten/{shortCode}/stats` | Retrieve history of a short URL |
| `GET` | `/swagger-ui/` | View API documentation |

## Contributing
Feel free to contribute by forking the repo, making changes, and submitting a pull request!
