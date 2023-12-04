# job4j_auth

## Описание проекта Аутентификация


ВОЗМОЖНОСТИ:

1. В системе ключевая модель Пользователь.

2. Проработанны функции:

  - возможность просмора всех пользователей прошедших аутентификацию в приложении;
  - поиск пользователя по его Id в приложении;
  - создание нового пользователя;
  - обновление данных пользователя;
  - удаление пользователя.

3. Поект реализует RestFul API архитектуру.


## Стек технологий

    Java 17
    SpringBoot 2.6.15
    Hibernate
    PostgreSQL 14

## Требования к окружению

    Java 17
    Maven 3.8
    PostgreSQL 14

## Запуск проекта

1. В PostgreSQL создать базу данных fullstack_auth
   jdbc:postgresql://127.0.0.1:5432/fullstack_auth
2. Собрать jar файл с помощью mvn install
3. Запустить приложение с помощью собранного jar-файла
java -jar target/job4j_persons-1.0.jar
4. Перейти по адресу http://localhost:8080/index


