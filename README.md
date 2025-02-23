<h1>Vendi - E-commerce Backend API</h1>

<p>Vendi is a backend application that provides essential endpoints for an e-commerce store. It is built using Spring Boot and follows a modular structure to handle authentication, product management, user management, shopping cart functionality, and more. The frontend for this application is currently under development in React.</p>

### Features
<hr>

#### User Authentication & Authorization
- JWT-based authentication
- Role-based access control (Admin, User, etc.)
- Secure password storage with BCrypt

#### Product & Category Management
- CRUD operations for products and categories
- Category-based product filtering
- Review and rating system

#### Shopping Cart & Orders
- Add/remove items from the cart
- Apply coupons for discounts
- Order placement and tracking

#### Coupons & Discounts
- Create and manage discount coupons
- Validate and apply coupons during checkout

#### User Management
- Registration and login
- User profile updates
- Account status management

#### Security & Error Handling
- Global exception handling
- Custom error messages
- Secure API endpoints with Spring Security

#### Email Notifications
- Spring Mail integration for email verification and order confirmations

### Technologies
<hr>

- Java, Spring Boot, Spring Security (JWT), Spring Data JPA, Spring Mail, Lombok
- Database: MySQL
- Frontend: (Under Development) React, Redux, Tailwind CSS

### Project Structure

<pre><code>Vendi
│── config             # Configuration files (JWT, Security, etc.)
│── controller         # REST API controllers
│── domain             # Enums and constants
│── dto                # Data Transfer Objects
│   ├── request        # DTOs for incoming requests
│   ├── response       # DTOs for API responses
│── exception          # Custom exception handling
│── mapper             # Object mappers
│── model              # Entity classes for database mapping
│── repository         # JPA repositories for database interaction
│── service            # Business logic layer
│   ├── impl           # Implementations of service interfaces
│── Application.java   # Main Spring Boot Application
</code></pre>

### Installation & Setup
1. Clone the repository:
<pre><code>git clone https://github.com/KamilGlazer/Vendi.git
cd vendi
</code></pre>
2. Configure Spring Mail in application-dev.yml
<pre><code>EMAIL_USERNAME:
EMAIL_PASSWORD:
</code></pre>
3. Make sure you are in the /Vendi directory, then enter the following commands in your command line.
<pre><code>docker-compose up mysql -d 
mvn clean package
docker-compose up --build -d
</code></pre>


