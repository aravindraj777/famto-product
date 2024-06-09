# Product Management Spring Boot Application

This is a Product Management application built with Spring Boot (v3.3.0), MySQL for data storage, and Spring Security integrated with JWT authentication and role-based authorization.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Postman Collection](#postman-collection)
- [Security](#security)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This application allows an admin to manage merchants and merchants to manage their products. Both admin and merchants need to be authenticated using JWT tokens before performing any operations.

## Features

- **Admin**:
  - Add new merchants
  - List all merchants
  - Delete a merchant
  - Login

- **Merchant**:
  - Add new products
  - List all products
  - Delete a product
  - Login

## Technologies Used

- **Spring Boot 3.3.0**: Framework for building Java applications.
- **Spring Security**: Provides authentication and authorization.
- **JWT Authentication**: Secure token-based authentication mechanism.
- **MySQL**: Relational database management system.
- **JPA/Hibernate**: Java Persistence API for ORM (Object-Relational Mapping).
- **Maven**: Build and dependency management tool.

## Getting Started

### Prerequisites

- **Java 17 or higher**: Install Java Development Kit (JDK) from [Oracle's official website](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- **Maven**: Download from [official Maven website](https://maven.apache.org/download.cgi) or install via your package manager.
- **MySQL**: Download and install from [official MySQL website](https://dev.mysql.com/downloads/).

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/aravindraj777/product-management.git
   cd product-management
