/* Create database */
CREATE DATABASE JAthenaeum;

USE JAthenaeum;

/* Create tables */


/* Create table Utente */
CREATE TABLE Utente (
	NumeroTesseraUtente integer AUTO_INCREMENT PRIMARY KEY,
	EmailUtente varchar(50) NOT NULL,
	PasswordUtente varchar(50) NOT NULL,
	NomeUtente varchar(50),
	CognomeUtente varchar(50),
	DataNascitaUtente date
);


/* Create table Libro */
CREATE TABLE Libro (
	IDLibro integer AUTO_INCREMENT PRIMARY KEY,
	TitoloLibro varchar(50) NOT NULL,
	CopertinaLibro varchar(200),
	GenereLibro varchar(50),
	CodiceIsbnLibro varchar(13),
	DescrizioneLibro varchar(500)
);


/* Create table Prestito */
CREATE TABLE Prestito (
	IDPrestito integer AUTO_INCREMENT PRIMARY KEY,
	NumeroTesseraUtente integer NOT NULL,
	IDLibro integer NOT NULL,
	DataInizioPrestito date NOT NULL,
	DataFinePrestito date NOT NULL,
	FOREIGN KEY (NumeroTesseraUtente) REFERENCES Utente(NumeroTesseraUtente),
	FOREIGN KEY (IDLibro) REFERENCES Libro(IDLibro)
);


/* Create table Autore */
CREATE TABLE Autore (
	IDAutore integer AUTO_INCREMENT PRIMARY KEY,
	NomeAutore varchar(50) NOT NULL,
	CognomeAutore varchar(50) NOT NULL,
	FotoAutore varchar(200),
	DataNascitaAutore date,
	BiografiaAutore varchar(1000)
);


/* Create table Scrivere */
CREATE TABLE Scrivere (
	IDScrivere integer AUTO_INCREMENT PRIMARY KEY,
	IDLibro integer NOT NULL,
	IDAutore integer NOT NULL,
	FOREIGN KEY (IDLibro) REFERENCES Libro(IDLibro),
	FOREIGN KEY (IDAutore) REFERENCES Autore(IDAutore)
);

