Book Social Network
Welcome to the Book Social Network – a platform that connects book lovers, allowing users to easily sign up, borrow, renew, and return books, with timely reminders to ensure books are returned on schedule.

Table of Contents
Introduction
Features
Technologies Used
Installation
Environment Setup
Usage
API Endpoints
Future Enhancements
Contributing
License
Introduction
The Book Social Network is a social platform for book enthusiasts. Users can sign up, borrow books, and receive reminders to return them on time. With an easy-to-use interface and both OAuth and manual signup options, the application ensures users have a smooth onboarding experience. This system also supports OTP verification for secure and verified accounts.

Features
User Management
User Registration: Sign up via OAuth (Google, Facebook, etc.) or manual registration.
OTP Verification: Users receive a one-time password (OTP) to verify their account. Only verified accounts can borrow books.
Login and Authentication: Secure login with email and password.
Book Borrowing
Borrow Books: Users can browse and borrow books for a specified time.
Return Reminders: Automated reminders are sent before the return date to prompt users to return borrowed books on time.
Renewal: Users can renew their borrowing period if the book is available for renewal.
Notifications
Return Alerts: Timely reminders to users via email/SMS when the return date is near.
Renewal Notifications: Reminders if the book renewal is due.
Social Features
Friend Network: Connect with other users, share book recommendations, and discuss your favorite reads.
Technologies Used
Backend: Spring Boot, Java
Database: PostgreSQL
Frontend: React (or Angular/Vue if applicable)
Authentication: OAuth 2.0, JWT, and OTP verification
Notifications: Email/SMS integration for alerts and reminders
Containerization: Docker for environment consistency
Installation
To run this project locally, follow these steps:

Prerequisites
Java 17 or higher
Maven
Docker
PostgreSQL
Steps
Clone the repository:

bash
Copy code
git clone https://github.com/yourusername/book-social-network.git
cd book-social-network
Set up PostgreSQL database using Docker:

bash
Copy code
docker-compose up -d
Install dependencies and build the project:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
mvn spring-boot:run
Environment Setup
Configure the environment variables in application.yml:

yaml
Copy code
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: <your_db_username>
    password: <your_db_password>
  mail:
    host: localhost
    port: 1025
    username: <your_email_username>
    password: <your_email_password>
  oauth:
    google-client-id: <your_google_client_id>
    google-client-secret: <your_google_client_secret>
jwt:
  secret: <your_jwt_secret>
  expiration: 86400000  # 1 day in milliseconds
Usage
Sign Up: Users can sign up using OAuth or manual registration.
Account Verification: An OTP will be sent to the registered email for verification.
Borrow a Book: Once verified, users can borrow books for a specified duration.
Return Notifications: Users will receive notifications to return books when the due date is near.
Renewal Option: If available, users can extend the borrowing period.
API Endpoints
Below are some of the core API endpoints. Full documentation is available in /api-docs.

Authentication
POST /api/auth/signup - Register a new user
POST /api/auth/verify-otp - Verify OTP to activate account
POST /api/auth/login - Login with email and password
Books
GET /api/books - Get a list of available books
POST /api/books/borrow - Borrow a book
POST /api/books/renew - Renew borrowed book
Notifications
POST /api/notifications/send - Send a return or renewal reminder
Future Enhancements
Review and Rating System: Allow users to rate and review books.
Recommendation Engine: Suggest books based on user preferences and borrowing history.
Advanced Notifications: Integrate with other messaging services for enhanced notifications.
Contributing
We welcome contributions from the community. If you would like to contribute, please fork the repository and submit a pull request.

Fork the project
Create a new branch (git checkout -b feature-branch)
Commit your changes (git commit -m 'Add new feature')
Push to the branch (git push origin feature-branch)
Open a Pull Request
License
This project is licensed under the MIT License - see the LICENSE file for details.
