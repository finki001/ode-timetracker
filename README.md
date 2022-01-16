<!-- PROJECT LOGO -->
<br/>

<div align="center">
    <!-- <img src="images/logo.png" alt="Logo" width="80" height="80"> -->
  <!-- <h1 align="center">Time Tracker</h1> -->
</div>


<!--
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>
-->


<!-- ABOUT THE PROJECT -->

## About The Project

![Screenshot](images/preview.png)


<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Goal -->

## Goal

Die Firma X möchte ein Programm zum Tracken der Arbeitszeit ihrer Mitarbeiter. Diese sollen dort in der Lage sein, Tasks
hinzufügen und Zeiten darauf zu buchen. Die Zeiten sollen sich mit einem Online Service synchronisieren, damit diese
online gespeichert und abgerufen werden können.

<!-- Requirements -->

## Requirements

* Der Code sollte mit JavaDoc Kommentaren versehen sein.
* Eine readme.md sollte einen groben Überblick über das Projekt geben.
* Mehrere Klassen mit Vererbung, Overriding von Methoden und ggf mehren
* Konstruktoren in der Klasse
* Die Zugriffsrechte für Klassen, Methoden und Properties (Variablen) sollten sinnvoll gewählt werden
* Durchdachtes Exception Handling. Hierbei kann auf die Vorhandenen Exceptions
* zugegriffen werden, sofern diese für gerechtfertigt erscheinen.
* File IO in irgendeiner Art und Weise. (Logs, CSV Dateien, Properties, XML)
* Multithreading (Zeitintensive Berechnungen NICHT im Gui-Thread)
* Durchdachte und aufgeräumte GUI. (FXML oder Inline)
* Networking (Kommunikation von Server / Client Software über TCP / UDP)

<!-- ROADMAP -->

## Roadmap

- [x] Keep README up to date
- [ ] Zeit buchen
- [ ] Tasks einfügen
    - [ ] Notizen
- [ ] Online Sync
- [ ] Auswertung der Zeiten im Tool
- [ ] Export der Daten als .csv
- [ ] Vorgesetzte sehen aktuelle Zeitwerte

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- LICENSE -->

## License

Distributed under the ___ License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->

## Contact

* Lukas Fink - [Gitlab profile](https://es.technikum-wien.at/ic21b126)

* David Hahn - [Gitlab profile](https://es.technikum-wien.at/ic21b042)

* Isabel Westenberger - [Gitlab profile](https://es.technikum-wien.at/ic20b001)

<p align="right">(<a href="#top">back to top</a>)</p>

## Code organization

The project consists of 3 modules: commons, client and server. The commons module just holds model classes that are used
by the client and the server to avoid code duplication. Client and server are self explanatory IMHO.

## How to run the Client and Server

For the Server (start first): `./gradlew bootRun`<br>
For the Client:`./gradlew run`<br><br>
If this is your first start of the Server (<b>or the database under server/h2 has been deleted</b>) make sure to
set `spring.sql.init.mode` to `always` in the `application.properties` file.

### !!! You need to change this to `never` for consecutive starts !!!

<p align="right">(<a href="#top">back to top</a>)</p>

## Color codes

![colors](images/colors.png) 
