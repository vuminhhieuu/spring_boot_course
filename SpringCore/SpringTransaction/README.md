# Spring Transaction Demo

This project demo a simple banking transaction system using the Spring Framework, focusing on transactional integrity for account transfers.

## Features
- Transfer money between accounts with transaction management
- Account and transaction history management
- Database migration with Flyway

## Project Structure
```
SpringTransaction/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/springcore/
│   │   │   ├── Main.java
│   │   │   ├── config/AppConfig.java
│   │   │   ├── entity/Account.java
│   │   │   ├── entity/TransactionHistory.java
│   │   │   ├── repository/AccountRepository.java
│   │   │   ├── repository/HistoryRepository.java
│   │   │   └── service/TransferService.java
│   │   └── resources/db/migration/V1__init_scheme.sql
│   └── test/java/
└── target/
```

## Getting Started
### Prerequisites
- Java 17+
- Maven

### Setup
1. Clone the repository:


2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run Flyway migrations:
   ```bash
   mvn flyway:migrate
   ```
4. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.springcore.Main"
   ```

### Database
- Uses Flyway for schema migration (see `src/main/resources/db/migration/V1__init_scheme.sql`).
- Configure your database in `AppConfig.java`.

## How It Works
- `TransferService` handles money transfers between accounts.
- All transfers are transactional; if any step fails, changes are rolled back.
- Transaction history is recorded for each transfer.

