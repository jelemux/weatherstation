# Weather Station

## Quickstart

1. [Install SDKMAN!](https://sdkman.io/install)
2. Install JDK 17 (Adoptium Temurin): `sdk install java 17.0.2-tem`
3. Install Maven: `sdk install maven`
4. [Install MariaDB 10.3 or later](https://mariadb.org/download)
5. Create a database named `weatherstation`: `CREATE DATABASE weatherstation;`
6. Create a user `weatherstation` with password `weatherstation` and access to the `weatherstation` database:
   `GRANT ALTER,CREATE,DELETE,DROP,INSERT,SELECT,UPDATE ON weatherstation.* TO weatherstation@'localhost' IDENTIFIED BY 'weatherstation';`
7. Run with `mvn exec:exec` or create an executable JAR with `mvn package`
