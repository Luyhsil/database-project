CREATE TABLE Filme (
  FilmID INT NOT NULL PRIMARY KEY,
  Titel VARCHAR(100) NOT NULL,
  Bewertung TINYINT NOT NULL,
  Erscheinungsjahr SMALLINT NOT NULL
);

CREATE TABLE Schauspieler (
  SchauspielerID INT NOT NULL PRIMARY KEY,
  Name VARCHAR(100) NOT NULL
);

CREATE TABLE Regisseure (
  RegisseurID INT NOT NULL PRIMARY KEY,
  Name VARCHAR(100) NOT NULL
);

CREATE TABLE Genres (
  GenreID SMALLINT NOT NULL PRIMARY KEY,
  Name VARCHAR(32) NOT NULL
);


CREATE TABLE Filmteilnahmen (
  FilmID INT NOT NULL REFERENCES Filme(FilmID),
  SchauspielerID INT NOT NULL REFERENCES Schauspieler(SchauspielerID),
  CONSTRAINT Teilnahme PRIMARY KEY (FilmID, SchauspielerID)
);

CREATE TABLE Regiefuehrungen (
  FilmID INT NOT NULL REFERENCES Filme(FilmID),
  RegisseurID INT NOT NULL REFERENCES Regisseure(RegisseurID),
  CONSTRAINT Fuehrung PRIMARY KEY (FilmID, RegisseurID)
);

CREATE TABLE Genrezuordnungen (
  FilmID INT NOT NULL REFERENCES Filme(FilmID),
  GenreID INT NOT NULL REFERENCES Genres(GenreID),
  CONSTRAINT Zuordnung PRIMARY KEY (FilmID, GenreID)
);
