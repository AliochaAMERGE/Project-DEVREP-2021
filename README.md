# Projet DEVREP 2021

MISTRY Namrata
AMERGÉ Aliocha

A faire avant le 9/12

## Resources :

- create spring boot app :

https://spring.io/guides/gs/spring-boot/



## TODO : 

### Services coté clients

- [ ] Se connecter à son compte bancaire en utilisant plusieurs services d'authentification standards
- [ ] Manipuler, une fois connecté, son compte bancaire avec les opérations classiques de retrait, dépot et de transfert
- [ ] Utiliser des services de conversion de devise.

### Fonctionnalité côté administrateur

- [ ] Administrateur (uniquement un seul compte) possédent tout les droits possibles pour administrer l'ensemble du système (y compris créer des comptes pour les clients)
- [ ] Possibilité de fixer des informations globales pour la gestion de compte. (ex : définition du montant de découvert)

### Architecture de l'application

- [ ] n-tiers : Base de données <-> Application <-> Client
- [ ] microservices : à définir

### Logging

- [ ] Bibliothèque [LogBack](http://logback.qos.ch/index.html)

### Restitution du projet

- [ ] Code source (lien github)
- [ ] Documentation utilisateur
- [ ] Documentation administrateur
- [ ] Documentation déploiement, démarrage et connexion à l'application (Docker ou Vagrant possible)


## TODO with details :

- [x] creer un serveur Spring Boot
- [x] afficher une page de garde dummy
- [x] créer une base de donnnée mysql
- [x] creer une connection a une bases de donnée
- [x] lier la base de données et le serveur
- [x] creer une base de donne client solde (historique?)
- [ ] ajouter un historique sur la base de donnée
- [ ] afficher une vrai page de garde avec le front donné
- [ ] Sevice d'authentification
- [ ] admin page (password protected)






## Sources : 

- Administrateur 

https://www.baeldung.com/spring-boot-admin

- database (mysql)

https://spring.io/guides/gs/accessing-data-mysql/


## Work done :

creation du projet avec Spring boot initializer
Création d'un controller pour map ("/")
ajout de index.html dans resources/templates
ajout de spring-boot-starter-thymeleaf dans pom.xml
*-> rendu de la page*
ajout de data images scripts styles etc (de frontend/app) dans static
*Création de la database : https://spring.io/guides/gs/accessing-data-mysql/*
ajout de spring-boot-starter-data-jpa dans pom.xml
ajout de mysql-connector-java dans pom.xml
création de User.java
Création de UserRepository.java
création de DatabaseController.java
*-> test de la database sur /all et /demo*
ERROR
ajout des éléments dans application.properties
ERROR
./mvmn package
ERROR : java.sql.SQLException: Access denied for user 'springuser'@'localhost' (using password: YES)
User changed to root, and sudo mysql_secure_installation
le seveur se lance, mais pas les nouveaux path (add, demo et all)
Les paths marchent, et sont en fait ("/db/all" pour voir la db)
et `$ curl localhost:8080/demo/add -d name=First -d email=someemail@someemailprovider.com`
pour ajouter une valeurs
*-> database link ok*

