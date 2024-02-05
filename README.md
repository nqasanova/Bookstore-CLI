# Bookstore-CLI

## Overview
This Java application demonstrates a comprehensive implementation of a bookstore database, covering CRUD operations, transaction handling, and metadata access. The application is built to interact with a relational database, allowing users to manage books, authors, orders, and customers seamlessly.

## Table of Contents
 - [Installation](#installation)
    - [Clone the Repository](#1-clone-the-repository)
    - [Open Project in Java IDE](#2-open-project-in-java-ide)
    - [Create PostgreSQL Database](#3-create-postgresql-database)
 - [Usage](#usage)
    - [Configure Database Connection](#4-configure-database-connection)
    - [Add PostgreSQL Driver](#5-add-postgresql-driver)
    - [Run the Application](#6-run-the-application)
- [Features](#features)
- [Troubleshooting](#troubleshooting)

## Installation

### 1. Clone the Repository
   - Clone the repository to your local machine: `git clone https://github.com/nqasanova/Bookstore-CLI`

### 2. Open Project in Java IDE
   - Open the project in any Java-compatible IDE such as VS Code, IntelliJ, NetBeans, Eclipse, or BlueJ.

### 3. Create PostgreSQL Database
   - Using pgAdmin or any other PostgreSQL administration tool, execute SQL scripts located in `src -> SQL_Scripts`.
   - Copy and paste the scripts into the Query tool and run them.

## Usage

### 4. Configure Database Connection
   - Navigate to `src -> Connectivity -> Database_Connection` in the project.
   - Fill out the connection details:
      ```java
      String url = "jdbc:postgresql://host_name:port_number/database_name";
      String user = "username";
      String password = "password";
      ```
   - Replace placeholders with your PostgreSQL host, port, database name, username, and password.

### 5. Add PostgreSQL Driver
   - Add the `postgresql-42.7.0.jar` file from `src -> Dependency` to your project as a library or dependency.

### 6. Run the Application
   - Execute the main class located in `src -> Main -> Main.java`.

## Features

Upon running the application, the main menu will be displayed, presenting a list of available actions. Simply input the corresponding number to execute the desired action.

**Menu Options:**
1. Books table
2. Authors table
3. Customers table
4. Orders table
5. Books information table
6. Orders information table
7. Retrieve All Table names and Columns
8. Retrieve Column data types
9. Retrieve Primary keys
10. Retrieve Foreign keys
11. Exit from the application

## Troubleshooting

If you encounter any issues during installation or usage, you can refer to the following troubleshooting tips:

- **Issue**: Unable to connect to the database.
  - **Solution**: Double-check the database connection details provided in the configuration. Ensure that the PostgreSQL server is running and accessible from your environment.

- **Issue**: SQL scripts fail to execute.
  - **Solution**: Make sure you have the necessary permissions to create and modify databases. Check for any syntax errors in the SQL scripts.
