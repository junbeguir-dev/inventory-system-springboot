# Web-Based Inventory System

A simple deployable inventory management system built with free technologies:

- Java 17
- Spring Boot 3
- JSP/JSTL
- HTML, CSS, JavaScript
- Spring Data JPA / Hibernate
- H2 Database for free local development
- PostgreSQL support for free production-style database hosting
- Maven

## Features

- Dashboard with inventory summary
- Product CRUD
- Category management
- Supplier management
- Search products by SKU, name, or description
- Low-stock monitoring
- Quick stock increase/decrease buttons
- H2 browser console for development
- Optional PostgreSQL profile

## Requirements

Install these free tools:

1. Java JDK 17 or later
2. Maven 3.9 or later
3. Git
4. Optional: PostgreSQL if you want a production-style database

## Run Locally

```bash
mvn clean spring-boot:run
```

Open:

```text
http://localhost:8080
```

H2 Console:

```text
http://localhost:8080/h2-console
```

Use these H2 settings:

```text
JDBC URL: jdbc:h2:file:./data/inventorydb
Username: sa
Password: leave blank
```

## Run with PostgreSQL

Create a database first:

```sql
CREATE DATABASE inventorydb;
```

Then run:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

Or set environment variables:

```bash
export DATABASE_URL=jdbc:postgresql://localhost:5432/inventorydb
export DATABASE_USERNAME=postgres
export DATABASE_PASSWORD=yourpassword
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

## Package as JAR

```bash
mvn clean package
java -jar target/inventory-system-1.0.0.jar
```

## Upload to GitHub

```bash
git init
git add .
git commit -m "Initial inventory system"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/inventory-system.git
git push -u origin main
```

## Notes About GitHub Pages

GitHub Pages cannot run Java/Spring Boot apps because it only hosts static websites. Use GitHub as your source code repository, then deploy the Spring Boot app to a Java-capable host such as Render, Railway, Fly.io, Koyeb, or your own VPS. Their free tiers change over time, so check current availability before choosing.

## Project Structure

```text
src/main/java/com/example/inventory
  controller/     Web controllers
  model/          JPA entities
  repository/     Spring Data repositories
  service/        Business logic
  config/         Sample data seeder
src/main/resources/META-INF/resources/WEB-INF/jsp
  JSP pages
src/main/resources/static
  CSS and JavaScript
```

## Suggested Next Improvements

- Login/security with Spring Security
- Stock movement history table
- Purchase orders
- Barcode field
- CSV export
- REST API endpoints
- Dockerfile and cloud deployment workflow
