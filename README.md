[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7711943&assignment_repo_type=AssignmentRepo)
# l3m-PI-serveur : Partie serveur du Projet Intégrateur L3 MIAGE 2020-2021


# Description API HTTP 

## Chami CRUD
classe qui permet d'interagir avec la table chami dans la base de données.
avec des methodes que nous avons jujé necessaire decris ci dessous.
Api : api/users
```java @GetMapping``` : api/users/ retourne une liste de chamis dans la base de données.
voir ci dessous la signature de la methode

<< public List<Chami> allUsers(HttpServletResponse response);>>

```java @GetMapping("/{userId}") ``` : retourne un utilisateur dont l'identifiant est passé en parametre s'il existe dans la base.

<< public Chami read(@PathVariable(value = "userId") String email, HttpServletResponse response);>>

```java @PostMapping("/{userId}")```: cree un chami, au prealable verifie l''existance ou pas des identifiants dans la base.

  << public Chami create(@PathVariable(value = "userId") String email, @RequestBody Chami u,
            HttpServletResponse response);>>

```java @PutMapping("/{userId}")```: permet de faire la mise à jour des informations d'un chami.

 
  << public Chami update(@PathVariable(value = "userId") String email, @RequestBody Chami u,
            HttpServletResponse response);>>

 ```java @DeleteMapping("/{userId}")```: Effectue la suppression d'un chami dans la base de données
 
   << public void delete(@PathVariable(value = "userId") String id, HttpServletResponse response);>>
 

## Defi CRUD
Classe qui interagie avec la table Defi de la base de données à l'instar de Chami CRUD
Api : "/api/defis"
```java @GetMapping```: api/defis/ retourne une liste des defis dans la base de données? ci dessous la signature de la methode.

<< public List<Defi> allDefis(HttpServletResponse response);>>
  
```java @GetMapping("/{defi}")```: Retourne le defi dont l'identifiant est passé en parametre à condition qu'il existe dans la base.

   << public Defi read(@PathVariable(value = "defi") String id, HttpServletResponse response);>>

```java @PostMapping("/{defiId}")```: Cree un nouveau defi tout en respectant le principe d'unicité de l'identifiant dans la base.

    <<public Defi create(@PathVariable(value = "defiId") String email, @RequestBody Defi def;>>
            HttpServletResponse response);

```java @PutMapping("/{defiId}")```:Effectu la mise à jour des informations d'un defi.

    <<public Defi update(@PathVariable(value = "defiId") String email, @RequestBody Defi def,
            HttpServletResponse response)>>

```java @DeleteMapping("/{defiId}")```: Supprime un defi dont l'identifiant est passé en parametre dans la base de données.

    <<public void delete(@PathVariable(value = "defiId") String email, HttpServletResponse response);>>

```java @GetMapping("titre/{titre}")```: Retourne une liste de defi dont le titre est passé en parametre.

    <<public Set<Defi> getDefiByTitre(@PathVariable(value = "titre") String titre, HttpServletResponse response)>>

```java @GetMapping("chami/{login}")```: Retourne la liste de defi pour un chami passé en parametre.

 << public Set<Defi> getDefiyByChami(@PathVariable(value = "chami") String login, HttpServletResponse response)>>

```java @GetMapping("arret/{nomArret}")``` : Retourne la liste de defi pour un arret passé en parametre.

    << public Set<Defi> getDefiyByArret(@PathVariable(value = "nomArret") String nomArret, HttpServletResponse response);>>

## Arret CRUD
Classe qui interagie avec la table arret dans la base de données.

Api: "/api/arret"

```java @GetMapping```: Retourne la liste des arrets dans la base de données

<< public List<Arret> allArret(HttpServletResponse response)throws SQLException ; >>

```java @GetMapping("/{arretId}")```: Retourne la liste des arrets dont l'identifiant est passé en parametre.

   << public Arret read(@PathVariable(value = "arretId") int id, HttpServletResponse response) throws SQLException;>>

   ```java @PostMapping("/{arretId}")```: Cree un nouveau arret tout en assurant au prealable qu'elle n'existe pas dans la base.

<< public Arret create(@PathVariable(value = "arretId") int id, @RequestBody Arret u,
            HttpServletResponse response)throws SQLException;>>

```java @PutMapping("/{arretId}")```: Effectu la mise à jour des informations d'un arret.

    << public Arret update(@PathVariable(value = "arretId");>>

```java  @DeleteMapping("/{arretId}")```: Supprime un arret de la base de données.*

   << public void delete(@PathVariable(value = "arretId") int id, HttpServletResponse response)throws SQLException>>

 ```java @GetMapping("nl/{numeroligne}")```: Retourne la liste des arrets dont le numero de ligne qui y passe est passé en parametre.

   << public Set<Arret> getStopByNumbeLine(@PathVariable(value = "numeroligne")String numeroLine,HttpServletResponse response) throws SQLException;>>



## Etape CRUD
Classe qui interagie avec la table etape de la base données
Api : "/api/etapes"

 ```java GetMapping```: Retourne la liste des etapes dans la base de données.
  << public List<Etape> allEtape(HttpServletResponse response) throws SQLException; >>


```java  @GetMapping("/{etapeId}")```: Retourne l'etape dont l'identifiant est passé en parametre.

 << public Etape read(@PathVariable(value = "etapeId") int id, HttpServletResponse response);>>

 ```java @PostMapping("/{etapeId}")```: Cree une nouvelle etape en assurant qu'elle n'existe pas dans la base.

   << public Etape create(@PathVariable(value = "etapeId") int id, @RequestBody Etape etape,
            HttpServletResponse response);>>

```java @PutMapping("/{etapeId}")```: Effectu la mise à jour des information d'une etape.

    public Etape update(@PathVariable(value = "etapeId") int id, @RequestBody Etape etape, HttpServletResponse response)

```java @DeleteMapping("/{etapeId}")```: Supprime une etape dans la base données.

    << public void delete(@PathVariable(value = "etapeId") int id, HttpServletResponse response)>>

```java  @GetMapping("/deftitre/{deftitre}")```: Retourne la liste des etape pour un defi passé en parametre.

<< public Set<Etape> findReponeByIdVisite(@PathVariable(value = "deftitre") String titre, HttpServletResponse response);>>

## Reponse CRUD
Classe qui interagie avec la table reponse dans la de données.
Api: "/api/reponses": Retourne la liste des reponses dans la base de données.

```java @GetMapping("/deftitre/{deftitre}")``` : Retourne la liste des reponses d'un chami passé en parametre

<< public Set<Etape> findReponeByIdVisite(@PathVariable(value = "deftitre") String titre, HttpServletResponse response);>>



















































































A stub which can easily be deployed to Heroku.

This application supports the [Getting Started with Java on Heroku](https://devcenter.heroku.com/articles/getting-started-with-java) article - check it out.

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Running Locally

Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ mvn clean install
$ heroku local:start
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

If you're going to use a database, ensure you have a local `.env` file that reads something like this:

```
JDBC_DATABASE_URL=dbc:postgresql://HOST:PORT/DATABASE?sslmode=require&user=USER&password=PASSWORD
```

## Deploying to Heroku

Configure Heroku Deploying mode to GitHub so that you can automatically deploy on Heroku when pushing on GitHub.

## Documentation

For more information about using Java on Heroku, see these Dev Center articles:
uuuu
- [Java on Heroku](https://devcenter.heroku.com/categories/java)


##Teste de connextion vs code
jordan
