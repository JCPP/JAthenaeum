/* Create database */
CREATE DATABASE JAthenaeum;

USE JAthenaeum;

/* Create tables */


/* Create table User */
CREATE TABLE User (
	UserCardNumber integer AUTO_INCREMENT PRIMARY KEY,
	UserEmail varchar(50) NOT NULL,
	UserPassword varchar(50) NOT NULL,
	UserName varchar(50),
	UserSurname varchar(50),
	UserBornDate date
);


/* Create table Book */
CREATE TABLE Book (
	BookID integer AUTO_INCREMENT PRIMARY KEY,
	BookTitle varchar(50) NOT NULL,
	BookCover varchar(200),
	BookGenre varchar(50),
	BookIsbnCode varchar(13),
	BookDescription varchar(500)
);


/* Create table Loan */
CREATE TABLE Loan (
	IDLoan integer AUTO_INCREMENT PRIMARY KEY,
	UserCardNumber integer NOT NULL,
	BookID integer NOT NULL,
	LoanStartDate date NOT NULL,
	LoanEndDate date NOT NULL,
	FOREIGN KEY (UserCardNumber) REFERENCES User(UserCardNumber),
	FOREIGN KEY (BookID) REFERENCES Book(BookID)
);


/* Create table Author */
CREATE TABLE Author (
	AuthorID integer AUTO_INCREMENT PRIMARY KEY,
	AuthorName varchar(50) NOT NULL,
	AuthorSurname varchar(50) NOT NULL,
	AuthorPhoto varchar(200),
	AuthorBornDate date,
	AuthorBiography varchar(1000)
);


/* Create table Writes */
CREATE TABLE Writes (
	IDWrites integer AUTO_INCREMENT PRIMARY KEY,
	BookID integer NOT NULL,
	AuthorID integer NOT NULL,
	FOREIGN KEY (BookID) REFERENCES Book(BookID),
	FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID)
);

