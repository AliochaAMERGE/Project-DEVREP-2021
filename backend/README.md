# backend

## Exigences

- OpenJDK 11 (https://jdk.java.net/17/)
- Maven (https://maven.apache.org/)
- Mysql (https://www.mysql.com/)

## Exécuter

```sh
cd backend
```

- Créer une base de données MYSQl (client_bankapp).
- Ajouter vos identifiants de connection dans `./src/main/ressources/application.properties`.

```sh
mvn spring-boot:run
```
*Ou exécuter le fichier `backend/src/main/java/fr/su/bankapp/Application.java`*