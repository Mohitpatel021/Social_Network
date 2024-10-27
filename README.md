# Book Social Network

A social platform where users can sign up, verify their accounts, borrow books, receive timely reminders for returns, and renew their borrowing period. Built for book enthusiasts, the Book Social Network is designed to create an engaging community around book sharing and discussions.

---

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **User Registration & Authentication**

  - Users can sign up using OAuth or traditional email registration.
  - OTP-based verification ensures only verified users can access the platform.
- **Book Borrowing System**

  - Borrow books for a set period and get reminders for returns.
  - Option to renew borrowed books before the return deadline.
- **Notifications**

  - Email/SMS reminders to ensure timely book returns.
  - Renewal reminders for borrowed books nearing the due date.
- **Social Features**

  - Connect with other users, discuss books, and share recommendations.

---

## Technologies Used

- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Frontend**: React (or substitute based on your actual frontend framework)
- **Authentication**: OAuth 2.0, JWT, and OTP-based verification
- **Notifications**: Email/SMS integration
- **Containerization**: Docker

---

## Getting Started

### Prerequisites

- **Java**: Version 17 or higher
- **Maven**: For managing dependencies
- **Docker**: To run PostgreSQL and MailDev containers

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/book-social-network.git
   cd book-social-network
   ```
2. **Start Docker Containers** :

* Run PostgreSQL and MailDev using Docker Compose

```
  docker-compose up -d

```

  3.**Build the Project** :

* Install dependencies and build

  ```
  mvn clean install
  ```


    4.**Run the Application** :

* Start the Spring Boot application

```
mvn spring-boot:run

```


### Configuration

In your `application.yml`, ensure these configurations are set:

```
server:
  port: 8088

spring:
  datasource:
    url: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
    username: root
    password: root
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

  mail:
    host: localhost
    port: 1025
    username: <your_email_username>
    password: <your_email_password>

jwt:
  secret: <your_jwt_secret>
  expiration: 86400000  # 1 day in milliseconds

```

## Usage

1. **Sign Up** : Register using OAuth (Google, Facebook) or manually with email.
2. **Verify Account** : Receive an OTP to activate the account.
3. **Borrow Books** : Browse books and borrow for a specific time.
4. **Receive Notifications** : Get reminders for book returns and renewals.
5. **Renew Book Borrowing** : If allowed, renew the borrowing period

## API Endpoints

Here are some key API endpoints. (For complete API documentation, refer to `/api-docs`.)

* **Authentication**
  * `POST /api/auth/signup`: Register a new user
  * `POST /api/auth/verify-otp`: Verify OTP to activate account
  * `POST /api/auth/login`: Login with email and password
* **Book Management**
  * `GET /api/books`: List available books
  * `POST /api/books/borrow`: Borrow a book
  * `POST /api/books/renew`: Renew borrowed book
* **Notifications**
  * `POST /api/notifications/send`: Send return or renewal reminder

## Future Enhancements

* **Review and Rating System** : Allow users to rate and review books.
* **Book Recommendations** : Suggest books based on user preferences.
* **Enhanced Notifications** : Integrate with additional messaging services

## Contributing

Contributions are welcome! Follow these steps to contribute:

1. Fork the project.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

```

---

Now, simply copy this entire content and paste it into your `README.md` file in your project directory. This should be the complete README structure that you can use directly on GitHub.

```
