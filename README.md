# Projet DEVREP 2021

MISTRY Namrata
AMERGÉ Aliocha

## Comment exécuter le projet ?

- Dépendances : 
  
    - java 11
    - node 8.1.2
    - maven 3.6.3
    - mysql-server 8.0.27
    - @vue/cli 4.5.15 (optionnel)

### Connection à la base de donnée :

> Peut être modifier dans le fichier [application.properties](backend/src/main/resources/application.properties)

- mysql lancée sur le port 3306 (port par défault)
- Une base de donnée nommée `client_bankapp`
- une connection username=root, password=password

### Pour exécuter le projet : deux façons différentes

L'adresse du projet est **localhost:8081**, l'adresse de l'api est **localhost:8082/api**


Soit en lançant mvn package dans le dossier racine du projet, 
puis la `java -jar backend/target/bankapp.war` toujours dans le dossier racine.

Soit en lançant individuellement le backend (fichier [application.java](backend/src/main/java/fr/su/bankapp/Application.java))
Puis dans le dossier `./frontend` lancer les commandes :
- `npm install` (peut prendre un certain temps)
- `npm run serve`

### fonctionnalitées

Au lancement du site, il faudra choisir un identifiant de compte.
Une fois cela fait, vous aurez le droit de déposer (deposit) ou retirer de l'argent (withdraw) de votre compte.

Coté Admin :

Création d'un nouveau client :

- `localhost:8082/api/secured/create/{lastName}/{firstName}/{email}/{balance}/{overdraft}`
  
- `localhost:8082/api/secured/create/{lastName}/{firstName}/{email}/{balance}/{overdraft}`

En fonction des paramères nécéssaire.

le nom d'utilisateur est `user`
le mot de passe est indiqué dans les logs au lancement du serveur.