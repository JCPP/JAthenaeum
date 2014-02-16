JAthenaeum
==========

![JAthenaeum](https://raw.github.com/JCPP/JAthenaeum/master/WebContent/image/logoonlybook.png)

Java Web Application to handle a library.


Traccia
-------
Realizzare un’applicazione per la gestione di una biblioteca. L’applicazione deve gestire l’elenco dei libri posseduti dalla biblioteca e le prenotazioni degli stessi. Ogni libro è identificato da autore, titolo, genere, codice ISBN, breve descrizione e immagine della copertina. Possono esserci più copie dello stesso libro. Ogni autore è identificato da nome, cognome, data di nascita, biografia, foto. Ogni prenotazione è identificata dal nominativo che ha effettuato il prestito e suoi riferimenti, dalla data di inizio e fine prestito e dal libro prestato. Le specifiche sono:
  * Riconoscimento dell’utente tramite login (ad esempio con utenze predefinite);
  * Gestione autori: inserimento, modifica, ricerca, cancellazione;
  * Gestione libri: inserimento, modifica dati, ricerca (con visualizzazione copie totali e presenti in biblioteca), caricamento di copie nella libreria;
  * Prenotazione libri: inserimento, modifica, ricerca, cancellazione, inserimento del rientro del libro, visualizzazione elenco prestiti scaduti;
  * Logout.


Documentation
-------------


### Libraries ###

#### Java side ####

The project uses some libraries to simplify and facilitate the work for the creation of the web application:

* Struts;
* MySql connector.

For a more precise and detailed list, please take a look at the [ivy.xml](https://github.com/JCPP/JAthenaeum/blob/master/ivy.xml) file.

**Note**: most of the libraries that are downloaded after running the build, are dependencies of the libraries mentioned above.


#### Front-End side #####

The project uses [bootstrap](http://getbootstrap.com/), the most popular front-end framework for developing responsive, mobile first projects on the web.



### Tools ###

The project uses some tools to simplify and facilitate the work for the creation of the web application:

* [Eclipse](http://eclipse.org/): an integrated development environment (IDE);
* [Ivy](http://ant.apache.org/ivy/): The agile dependency manager.



What you need
-------------

* Java EE Eclipse or Eclipse with all the necessary plugin for Java EE;
* IvyDE;
* Tomcat v.6;
* Set the build path from **Build Path** -> **Configure Build Path...**
   * add JDK;
   * add Tomcat server.
* **Ivy** -> **Resolve**;
* Set Ant property: Window -> Preferences -> Ant -> Runtime -> Properties -> Add Property ->
  * Name: **basedir**
  * Value: **${project_loc}**
* Set **Targeted Runtimes** for the project;
* Press **F5** on the project name in the **Project Explorer**.


How it works
------------

1. Import in Eclipse;
2. Run build.xml (with **retrieve** option);
3. Go to [url](http://localhost:8080/JAthenaeum/index.do) (if your server is listening on 8080 port);
4. Laugh hysterically
