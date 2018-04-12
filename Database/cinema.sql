CREATE DATABASE cinema;

USE cinema;

CREATE TABLE Genere(
	id_genere INTEGER AUTO_INCREMENT,
	descrizione VARCHAR(50),
  PRIMARY KEY (id_genere)
);

CREATE TABLE Film(
	id_film INTEGER AUTO_INCREMENT,
	titolo VARCHAR(100),
	id_genere INTEGER,
	url_locandina VARCHAR(50),
	url_trailer VARCHAR(50),
	durata INTEGER,
	trama VARCHAR(1000),
  PRIMARY KEY (id_film),
	FOREIGN KEY (id_genere) REFERENCES Genere(id_genere)
);

CREATE TABLE Sala(
	id_sala INTEGER AUTO_INCREMENT,
	descrizione VARCHAR(50),
	PRIMARY KEY (id_sala)
);

CREATE TABLE Spettacolo(
	id_spettacolo INTEGER AUTO_INCREMENT,
	id_film INTEGER,
	id_sala INTEGER,
	data_ora TIMESTAMP,
	PRIMARY KEY (id_spettacolo),
	FOREIGN KEY (id_film) REFERENCES Film(id_film),
	FOREIGN KEY (id_sala) REFERENCES Sala(id_sala)
);

CREATE TABLE Prezzo(
	id_prezzo INTEGER AUTO_INCREMENT,
	tipo VARCHAR(20),
	prezzo FLOAT,
	PRIMARY KEY (id_prezzo)
);

CREATE TABLE Posto(
	id_posto INTEGER AUTO_INCREMENT,
	id_sala INTEGER,
	riga INTEGER,
	poltrona INTEGER,
	esiste BOOLEAN,
	PRIMARY KEY (id_posto),
	FOREIGN KEY (id_sala) REFERENCES Sala(id_sala)
);

CREATE TABLE Ruolo(
	id_ruolo INTEGER AUTO_INCREMENT,
	ruolo VARCHAR(20),
	PRIMARY KEY (id_ruolo)
);

CREATE TABLE Utente(
	id_utente INTEGER AUTO_INCREMENT,
	id_ruolo INTEGER,
	email VARCHAR(50) UNIQUE,
	password VARCHAR(50),
	credito FLOAT,
	PRIMARY KEY (id_utente),
	FOREIGN KEY (id_ruolo) REFERENCES Ruolo(id_ruolo)
);

CREATE TABLE Prenotazione(
	id_prenotazione INTEGER AUTO_INCREMENT,
	id_utente INTEGER,
	id_spettacolo INTEGER,
	id_prezzo INTEGER,
	id_posto INTEGER,
	data_ora_operazione TIMESTAMP,
	PRIMARY KEY (id_prenotazione),
	FOREIGN KEY (id_utente) REFERENCES Utente(id_utente),
	FOREIGN KEY (id_spettacolo) REFERENCES Spettacolo(id_spettacolo),
	FOREIGN KEY (id_prezzo) REFERENCES Prezzo(id_prezzo),
	FOREIGN KEY (id_posto) REFERENCES Posto(id_posto)
);
