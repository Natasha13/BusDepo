# Project BusPark

Web application to manage Buspark

## The task 

Система Автопарк. В систему могут зайти Водители и
Администраторы. В Автопарке есть автобусы, маршруты. 
Администратор может назначать на Маршруты свободные автобусы, 
в автобусы свободных Водителей, а также освобождать их, 
делая свободными. Водитель может увидеть свое место работы, 
а также он должен подтвердить свое новое Назначение.

## Prerequisites

You need to have MySQL, Tomcat, Java 8+, Maven installed.

## Compile and package the application

```
mvn clean package
```

## Install and run

1. Create a database in MySQL and apply the script from :
`BusDepo\src\main\resources\database.sql`
2. Copy WAR file from `BusDepo\target` directory to Tomcat `webapps` directory and rename it to `ROOT.war`
3. Delete `ROOT` directory from `webapps` directory
4. Configure database connection:
For LINUX:
```
export CATALINA_OPTS='-DdbUserName=root -DdbUserPassword=root -DdbHost=localhost'
```
or for WINDOWS
```
set CATALINA_OPTS=-DdbUserName=root -DdbUserPassword=root -DdbHost=localhost
```
5. Start Tomcat `bin/catalina.sh start`(for LINUX) or `bin\catalina.bat start`(for WINDOWS)
6. Open `http:\\localhost:8080`
