# Service zur Berechnung einer Versicherungsprämie

Dieses Projekt nutzt eine **Microservice-Architektur**, um eine **Versicherungsprämie** basierend auf der jährlichen Kilometerleistung, dem Fahrzeugtyp und der Region der Fahrzeugzulassung zu berechnen.

---

## Vorbedingungen

Folgende Tools werden für die Ausfürhung der Anwendung benötigt: 

- **Docker**
- **docker-compose**

---



## Verwendung der Services

### 1. Docker-Container Setup


```bash
docker-compose up --build -d
```

### 2. UI in Browser öffnen
http://localhost:8081/

---

## Microservice Architektur des Projekts: 

Die Anwendung unterteilt sich in die folgenden drei Komponeten:
### 1. insurance-common: Libary, die die Elemente enthält, die von beiden Services verwendet werden: InsurancePremiumRequest und InsurancePremiumResponse.
 ![insurance-common Klassendiagram](.\classdiagrams\common.png)

### 2. insure-premium-service: Service der die Usereingabe übernimmt und die Daten zur Versicherungsprämie in der Datenbank abspeichert.
 ![insure-premium-service Klassendiagram] (.\classdiagrams\insure-premium-class.png)

### 3. premium-calculator: Dieser Service kapselt die Berechnungslogik der Versicherungsprämie. 
![premium-calculator Klassendiagram] (.\classdiagrams\premium-calculator.png)


## Zusammenspiel der Komponenten

Die Kommunikation zwischen den beiden Services erfolgt über **HTTP-Nachrichten**. Der **premium-calculator** stellt eine **REST-Schnittstelle** bereit, die vom **insurance-common** über **POST-Anfragen** angesprochen wird.

![Architecture Diagram](.\classdiagrams\architecture.png)

Die vollständige Beschreibung der REST-Endpunkte ist in der Datei **insurance-premium-endpoints.yml** zu finden.

Durch die Aufteilung der Services – einen für die **Berechnungslogik** und einen für die **Usereingabe und Datenverwaltung** – wird gewährleistet, dass Änderungen an der Berechnungslogik keine Auswirkungen auf die Funktionalitäten des anderen Services haben.

---

## Technologien und Frameworks

### Datenbank

Für die Datenverwaltung wird eine PostgreSQL Datenbank verwendet.
PostgreSQL ist eine leistungsstarke und für Webanwendungen geeignete relationale Datenbank. 
Sie bietet vollständige ACID-Kompatibilität und ist daher besonders für komplexe Transaktionen geeignet. 
Als für dieses Projekt entschiedener Vorteil bietet PostgreSQL ein hohes Maß an Skalierbarkeit (ermöglicht jeweils vertikale und horizontale Skalierung ) und an Wartbarkeit (Open-Source-Community).

### Spring Boot
Die Services sind in Form von Springboot Applikationen umgesetzt worden. Dieses Framework wurde ausgewählt, dass es sich gerade in den Bereichen der Wartbarkeit (bspw. durch Dependency Injections)
und der Testbarkeit (Spring Boot Test, MockMvc, Testcontainers, JUnit, Mockito) auszeichnet. 

### Test-Framework 


---
## Verbesserungspotential:

 - Die aktuelle GUI ist sehr einfach gehalten und soll nur der Usereingabe dienen. Eine zukünftige UI könnte mit React umgesetzt werden.
 - Die Authentifizierung optimieren: Statt Basic Auth und hartcodierter Authentifizierung könnte ein Authentication Provider wie Azure AD oder OAuth verwendet werden, um:
 - Umfassenderes Error Handling umsetzten