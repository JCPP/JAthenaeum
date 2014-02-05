/* Create database */
CREATE DATABASE JAthenaeum;

USE JAthenaeum;

/* Create tables */


/* Create User table */
CREATE TABLE User (
	UserID integer AUTO_INCREMENT PRIMARY KEY,
	UserEmail varchar(50) NOT NULL,
	UserPassword varchar(50) NOT NULL,
	UserName varchar(50),
	UserSurname varchar(50),
	UserBornDate date
);


/* Create Customer table */
CREATE TABLE Customer (
	CustomerCardNumber integer AUTO_INCREMENT PRIMARY KEY,
	CustomerEmail varchar(50) NOT NULL,
	CustomerName varchar(50) NOT NULL,
	CustomerSurname varchar(50) NOT NULL
);


/* Create Book table */
CREATE TABLE Book (
	BookID integer AUTO_INCREMENT PRIMARY KEY,
	BookTitle varchar(50) NOT NULL,
	BookCover varchar(200),
	BookGenre varchar(50),
	BookIsbnCode varchar(13),
	BookDescription varchar(500)
);


/* Create Loan table */
CREATE TABLE Loan (
	IDLoan integer AUTO_INCREMENT PRIMARY KEY,
	CustomerCardNumber integer NOT NULL,
	BookID integer NOT NULL,
	LoanStartDate date NOT NULL,
	LoanEndDate date NOT NULL,
	FOREIGN KEY (CustomerCardNumber) REFERENCES Customer(CustomerCardNumber),
	FOREIGN KEY (BookID) REFERENCES Book(BookID)
);


/* Create Author table */
CREATE TABLE Author (
	AuthorID integer AUTO_INCREMENT PRIMARY KEY,
	AuthorName varchar(50) NOT NULL,
	AuthorSurname varchar(50) NOT NULL,
	AuthorPhoto varchar(200),
	AuthorBornDate date,
	AuthorBiography varchar(1000)
);


/* Create Writes table */
CREATE TABLE Writes (
	WritesID integer AUTO_INCREMENT PRIMARY KEY,
	BookID integer NOT NULL,
	AuthorID integer NOT NULL,
	FOREIGN KEY (BookID) REFERENCES Book(BookID),
	FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID)
);

