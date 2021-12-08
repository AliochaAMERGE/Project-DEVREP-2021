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





TODO with sources : 

- register an account (if already logged in)
https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial




@Question ❓

- Faire deux bases de données (login & account data)


TODO : 

gérer les erreurs en cas de clients non existant





routes : 

REST : 
    upon client connection
    - get client id from email password
    - get client account from id
    upon secured connection
    - create new account
    - delete account
    - change account informations (like overdraft)

route : 
    client side : 
    - main page
    secured :
    - creation menu ??





TODO

LOGIN via AUTH  (firebase)
LOGIN VIA MAIL ET MDP (ou pas) (route /login "simple")

Lire le compte en fonction de l'adresse mail (puis l'id) 

Faire le requete REST pour creer un utilisateur (ou modifier)

docker de la bdd (docker start ?)

README explication executer

(lancer indepedemment :  ./mvnm spring-boot:run   puis npm run serve et url localhost:8081)

lancer tout en meme temps :  creation du package et java -jar backend/target/bankapp.war

