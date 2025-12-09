# Store API

Enterprise-grade RESTful API for e-commerce order management, built with Spring Boot 3 and modern Java practices.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![API Documentation](https://img.shields.io/badge/API%20Docs-Swagger-85EA2D.svg)](http://localhost:8080/swagger-ui.html)

## Stack

- **Java 21** | **Spring Boot 3.5** | **Spring Security 6** with JWT
- **JPA/Hibernate** | **PostgreSQL** (prod) | **H2** (test)
- **AWS S3** integration | **JavaMail** | **SpringDoc OpenAPI 3**

## Architecture

```
├── config/          Security, OpenAPI, S3, Jackson configurations
├── dto/             Data Transfer Objects with validation
├── resources/       REST controllers with OpenAPI documentation
├── services/        Business logic layer
├── repositories/    JPA repositories
└── security/        JWT authentication & authorization
```

## Quick Start

```bash
# Set environment variables
export JWT_SECRET=your_secret
export AWS_ACCESS_KEY_ID=your_key
export AWS_SECRET_ACCESS_KEY=your_secret
export S3_BUCKET=your_bucket

# Run
./mvnw spring-boot:run
```

### 📖 Quick Access

| Resource | URL |
|----------|-----|
| **Swagger UI** | http://localhost:8080/swagger-ui.html |
| **OpenAPI JSON** | http://localhost:8080/v3/api-docs |
| **API Base URL** | http://localhost:8080/api/v1 |

### 🏷️ API Tags

<table>
  <tr>
    <td><b>🔐 Authentication</b></td>
    <td>Token refresh & password recovery</td>
  </tr>
  <tr>
    <td><b>👥 Clients</b></td>
    <td>User management & profile pictures</td>
  </tr>
  <tr>
    <td><b>📦 Products</b></td>
    <td>Product catalog with search & filters</td>
  </tr>
  <tr>
    <td><b>🏷️ Categories</b></td>
    <td>Product categorization</td>
  </tr>
  <tr>
    <td><b>🛒 Orders</b></td>
    <td>Order management & history</td>
  </tr>
  <tr>
    <td><b>📍 States</b></td>
    <td>Geographic data (states & cities)</td>
  </tr>
</table>

## Key Features

- **JWT Authentication** with refresh token mechanism
- **Role-based Access Control** (ADMIN, CLIENT)
- **Image Upload** to AWS S3 with automatic resizing
- **Email Notifications** for orders and password recovery
- **Advanced Search** with pagination, sorting, and filtering
- **Comprehensive API Documentation** via OpenAPI 3.0
- **Custom Exception Handling** with standardized error responses
- **Bean Validation** with custom validators

## API Documentation

Interactive API documentation is available via **Swagger UI** once the application is running:

```
http://localhost:8080/swagger-ui.html
```

### Swagger Features

- **Interactive Testing**: Test all endpoints directly from the browser
- **Authentication**: Use the "Authorize" button to set your JWT token
- **Request/Response Examples**: View sample payloads and responses
- **Model Schemas**: Explore DTOs and domain objects

### OpenAPI Spec

Access the raw OpenAPI 3.0 specification at:
```
http://localhost:8080/v3/api-docs
```

---

## API Endpoints

### Public
```
GET    /api/v1/products          # Search products (paginated)
GET    /api/v1/categories        # List categories
GET    /api/v1/states            # List states & cities
POST   /auth/forgot              # Password recovery
```

### Authenticated
```
POST   /auth/refresh_token       # Refresh JWT
GET    /api/v1/clients/me        # Current user profile
POST   /api/v1/clients/picture   # Upload profile picture
POST   /api/v1/orders            # Create order
GET    /api/v1/orders/page       # List user orders
```

### Admin Only
```
POST   /api/v1/categories        # Create category
PUT    /api/v1/categories/{id}   # Update category
DELETE /api/v1/clients/{id}      # Delete client
GET    /api/v1/clients/page      # List all clients
```

## Configuration Profiles

- **test**: H2 in-memory database, mock email
- **dev**: PostgreSQL, development settings
- **prod**: Production-ready with AWS integration

## Security

JWT tokens are issued upon successful authentication and must be included in the `Authorization` header:
```
Authorization: Bearer <token>
```

Tokens expire after 24h by default. Use `/auth/refresh_token` to obtain a new token.

## Build & Deploy

```bash
# Build
./mvnw clean package

# Run with profile
java -jar target/demo-springboot-1.0.0-SNAPSHOT.jar --spring.profiles.active=prod

# Docker (if applicable)
docker build -t store-api .
docker run -p 8080:8080 store-api
```

## Testing

```bash
./mvnw test
```

## Environment Variables

| Variable                | Description                | Required               |
|-------------------------|----------------------------|------------------------|
| `JWT_SECRET`            | Secret key for JWT signing | Yes                    |
| `JWT_EXPIRATION`        | Token expiration time (ms) | No (default: 86400000) |
| `AWS_ACCESS_KEY_ID`     | AWS access key             | Yes (prod)             |
| `AWS_SECRET_ACCESS_KEY` | AWS secret key             | Yes (prod)             |
| `S3_BUCKET`             | S3 bucket name             | Yes (prod)             |
| `MAIL_USERNAME`         | SMTP username              | Yes (prod)             |
| `MAIL_PASSWORD`         | SMTP password              | Yes (prod)             |

## License

Apache 2.0 - See [LICENSE](LICENCE.md)

