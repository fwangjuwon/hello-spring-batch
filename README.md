## 개발 환경
• Java 8, Docker 20.10.5, MySQL 5.7
## 기술 스택
• Spring Boot 2.5, Spring Boot Batch 2.5, Spring Boot Data

• JPA 2.5, Junit 5.7.2, lombok 1.18.20, gradle 6.8

### docker 설정
```agsl
version: '3'

services:
  mysql:
    container_name: mysql_house
    image: mysql/mysql-server:5.7
    environment:
      MYSQL_ROOT_HOST: '%'
      MYSQL_USER: "house"
      MYSQL_PASSWORD: "house"
      MYSQL_DATABASE: "house"
    ports:
      - "3306:3306"
    command:
      - "mysqld"
      - "--character-set-server=utf8mb4"
      - "--collation-server=utf8mb4_unicode_ci"
```