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

- [ ] creer un serveur Spring Boot
- [ ] afficher une page de garde dummy
- [ ] créer une base de donnnée mysql
- [ ] creer une connection a une bases de donnée
- [ ] lier la base de données et le serveur
- [ ] creer une base de donne client solde (historique?)
- [ ] ajouter un historique sur la base de donnée
- [ ] afficher une vrai page de garde avec le front donné
- [ ] Sevice d'authentification
- [ ] admin page (password protected)

## Limites actuelles :

- mysql database accéder par l'utilisateur root ayant tout les droits, et pas de protection de mot de passe
- url admin "protégé" par un mot de passe en dur, et donc accessible par tous




## Sources : 

- Administrateur 

https://www.baeldung.com/spring-boot-admin

- database (mysql)

https://spring.io/guides/gs/accessing-data-mysql/


**Securing with https://spring.io/guides/gs/securing-web/**

Création du fichier MvcConfig.java

Ajout de spring-boot-starter-security dans pom.xml

Ajout de spring-security-test dans pom.xml

Création de home.html

Création de WebSecurityConfig.java

modification de helo.html & création de login.html

*-> sur url /hello, demande de mot de passe user,password ok*

password protected url changed to /admin

**Authentification with OAuth2**

~~https://spring.io/guides/tutorials/spring-boot-oauth2/~~

https://www.baeldung.com/spring-security-5-oauth2-login

Google credentials :  https://console.cloud.google.com/apis/credentials?project=devrep-bankapp&supportedpurview=project

ID Client : 1009894793512-apqco8t8p6al9j7caro49irc42p25og9.apps.googleusercontent.com

Code secret client : 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


## RE TODO

https://spring.io/guides/tutorials/react-and-spring-data-rest/

https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference


- Video magique :

https://www.youtube.com/watch?v=G46fjVzQ7BQ