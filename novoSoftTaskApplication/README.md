# novoSoftTaskApplication

## Project Setup

### 1. Dependencies

Add the following dependencies to your `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Springdoc OpenAPI UI -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-ui</artifactId>
        <version>1.6.8</version>
    </dependency>

    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>

# 2. Application Properties
Configure the src/main/resources/application.properties file:
# Server Port
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/novoSoftTaskApplication
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Spring Security Configuration
spring.security.user.name=hello
spring.security.user.password=hello

### 3. Annotations and Configurations
1. Spring Boot Annotations
@SpringBootApplication: Marks the main entry point of the Spring Boot application, combining @Configuration, @EnableAutoConfiguration, and @ComponentScan.
2. Controller Annotations
@RestController: Marks the class as a RESTful web service controller, combining @Controller and @ResponseBody.
@RequestMapping: Maps HTTP requests to handler methods of MVC and REST controllers.
3. Request Mapping Annotations
@GetMapping: Handles GET requests.
@PostMapping: Handles POST requests.
@PutMapping: Handles PUT requests.
@DeleteMapping: Handles DELETE requests.
@PathVariable: Binds a method parameter to a URI template variable.
@RequestBody: Binds the HTTP request body to a method parameter.
@Valid: Triggers validation on method parameters (used with @RequestBody).
4. Swagger Configuration
@Configuration: Marks the class as a source of bean definitions.
@EnableSwagger2: Enables Swagger 2 for API documentation (replaced by Springdoc OpenAPI in this project).
Springdoc OpenAPI Configuration: Automatically generates API documentation.
5. Security Configuration
@Configuration: Indicates that the class declares one or more @Bean methods.
@EnableWebSecurity: Enables Spring Security’s web security support.
@Bean: Defines a Spring bean.
6. Database Configuration
Database Setup

Database: MySQL (or PostgreSQL, H2)
Database Name: novoSoftTaskApplication
JDBC URL: jdbc:mysql://localhost:3306/novoSoftTaskApplication
Username: root
Password: your_password
Dependencies Ensure the MySQL connector dependency is added in pom.xml.

### API Usage Examples
1. Get All Products
Endpoint: GET /products
Description: Retrieves a list of all products.

2. Get Product by ID
Endpoint: GET /products/{id}
Description: Retrieves a specific product by its ID.
Path Parameters:

id: The ID of the product (e.g., 1).
3. Create Product
Endpoint: POST /products
Description: Creates a new product.

4. Update Product
Endpoint: PUT /products/{id}
Description: Updates an existing product.
Path Parameters:

id: The ID of the product (e.g., 1).

5. Delete Product
Endpoint: DELETE /products/{id}
Description: Deletes a product by its ID.
Path Parameters:

id: The ID of the product (e.g., 1).


