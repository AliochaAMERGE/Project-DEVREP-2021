# SU – Master 2 – UE DEVREP

## JEE – Projet : application web de services bancaires (web application for banking services)

MISTRY Namrata
AMERGÉ Aliocha

The objective of this project is to develop a simple banking services application, using
using Spring technologies. This project is to be done in pairs, but it can also be done
be done alone.

## Frameworks used

    - java 11
    - node 8.1.2
    - maven 3.6.3
    - mysql-server 8.0.27


## Database

- mysql
- Database name : `client_bankapp`

## Connection

- localhost:8082/api :  backend route
- localhost:8081     :  frontend route

## Divers informations

The account creation are handled upon authentication.
For test purpose, you can create a new account with mysql, or by sending a request to 
`localhost:8082/secured/create/{lastName}/{firstName}/{email}`
`localhost:8082/secured/create/{lastName}/{firstName}/{email}/{balance}/{overdraft}`
The request need to be authenticated (username : user, password : display on console upon server start)

