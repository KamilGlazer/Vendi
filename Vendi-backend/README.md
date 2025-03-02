<h1>Vendi - E-commerce Backend API 💻</h1>

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
- Validate and apply coupons

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

- Java, Spring Boot, Spring Security (JWT), Spring Data JPA, Spring Mail, Lombok, Docker
- Database: MySQL
- Frontend: (Under Development) React, Redux, Tailwind CSS

### Project Structure
<hr>

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
<hr>
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

### Api Endpoints
<hr>

### Authentication
- **POST** `api/auth/register` - User Registration
- **POST** `api/auth/login` - User Login

### Product 
- **GET** `api/products` - Get all products
- **GET** `api/products/{id}` - Get product
- **GET** `api/products/getByCategory/{categoryId}` - Get product by category id
- **GET** `api/products/search` - Get products by query
- **POST** `api/products` - Create a new product (Admin only)
- **PUT** `api/products/updateProduct/{id}` - Update a product (Admin only)
- **DELETE** `api/products/{id}` - Delete a product (Admin only)

### Review
- **GET** `api/review` - Get reviews by product id
- **GET** `api/review/{id}` - Get review by id
- **POST** `api/review/addReview` - Add review
- **DELETE** `api/review/{id}` - Delete review (Customer and Admin)

### Cart
- **GET** `api/cart` - Get all cart items
- **GET** `api/cart/{id}` - Get cart item by id
- **POST** `api/cart` - Add item to cart
- **POST** `api/cart/coupon` - Add coupon to cart
- **DELETE** `api/cart/{itemId}` - Remove item from cart 
- **DELETE** `api/cart` - Remove all items from cart

### Coupon
- **GET** `api/coupon` - Get all coupons (Admin only)
- **POST** `api/coupon` - Create a coupon (Admin only)
- **PUT** `api/coupon/{id}` - Edit a coupon (Admin only)
- **DELETE** `api/coupon/{id}` - Delete a coupon (Admin only)

### User
- **GET** `api/user/profile` - Get user details
- **POST** `api/user/addAddress` - Add address
- **PUT** `api/user/{id}` - Delete address

### Category
- **GET** `api/category` - Get all categories
- **GET** `api/category/{id}` - Get category by id
- **GET** `api/category/level/{level}` - Get categories by level
- **GET** `api/category/parent/{parentId}` -  Get categories by parent id
- **POST** `api/category` - Create category (Admin only)
- **PUT** `api/category/{id}` - Update category (Admin only)
- **DELETE** `api/category/{id}` - Delete category (Admin only)


### Future Enhancements
- Implementing payment using Stripe
- Adding order history and tracking
- Deploying the application to the cloud

<i>Frontend Development: The frontend is currently being developed in React...</i>

