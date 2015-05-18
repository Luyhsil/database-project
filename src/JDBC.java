import java.sql.*;

public class JDBC {
	public static void main(String[] args){
		//Statement Filme = null;
		try{
			Class.forName("org.postgresql.Driver"); // HIER
			String url = "jdbc:postgresql://localhost:15432/myapp?user=myapp&password=dbpass"; // HIER
			Connection conn = DriverManager.getConnection(url);
			
			Statement Filme = conn.createStatement();
			String tFilme = "CREATE TABLE IF NOT EXISTS Filme " +
							"(FilmID INT NOT NULL PRIMARY KEY, " +
							" Titel VARCHAR(100) NOT NULL, " +
							" Bewertung REAL NOT NULL, " +
							" Erscheinungsjahr SMALLINT NOT NULL, "+
							" GenreID SMALLINT NOT NULL REFERENCES Genres(GenreID))";
			Filme.executeUpdate(tFilme);
			
			Statement Schauspieler = conn.createStatement();
			String tSchauspieler = "CREATE TABLE IF NOT EXISTS Schauspieler " +
								   "(SchauspielerID INT NOT NULL PRIMARY KEY, " +
								   " Name VARCHAR(100) NOT NULL)";
			Schauspieler.executeUpdate(tSchauspieler);
			
			Statement Regisseure = conn.createStatement();
			String tRegisseure = "CREATE TABLE IF NOT EXISTS Regisseure " +
								  "(RegisseurID INT NOT NULL PRIMARY KEY, " +
								  " Name VARCHAR(100) NOT NULL)";
			Regisseure.executeUpdate(tRegisseure);
			
			Statement Genres = conn.createStatement();
			String tGenres = "CREATE TABLE IF NOT EXISTS Genres " +
							 "(GenreID SMALLINT NOT NULL PRIMARY KEY, " +
							 " Name VARCHAR(32) NOT NULL)";
			Genres.executeUpdate(tGenres);
	
			Statement Filmteilnahmen = conn.createStatement();
			String tFilmteilnahmen = "CREATE TABLE IF NOT EXISTS Filmteilnahmen " + 
									 "(FilmID INT NOT NULL REFERENCES Filme(FilmID), " +
									 " SchauspielerID INT NOT NULL REFERENCES Schauspieler(SchauspielerID), " +
									 " CONSTRAINT Teilnahme PRIMARY KEY (FilmID, SchauspielerID))";
			Filmteilnahmen.executeUpdate(tFilmteilnahmen);
			
			Statement Regieführungen = conn.createStatement();
			String tRegieführungen = "CREATE TABLE IF NOT EXISTS Regiefuehrungen " +
									 "(FilmID INT NOT NULL REFERENCES Filme(FilmID), " +
									 " RegisseurID INT NOT NULL REFERENCES Regisseure(RegisseurID), " +
									 " CONSTRAINT Fuehrung PRIMARY KEY (FilmID, RegisseurID))";
			Regieführungen.executeUpdate(tRegieführungen);
			
			Statement Genrezuordnungen = conn.createStatement();
			String tGenrezuordnungen = "CREATE TABLE IF NOT EXISTS Genrezuordnungen " + 
									   "(FilmID INT NOT NULL REFERENCES Filme(FilmID), " +
									   " GenreID INT NOT NULL REFERENCES Genres(GenreID), " +
									   " CONSTRAINT Zuordnung PRIMARY KEY (FilmID, GenreID))";
			Genrezuordnungen.executeUpdate(tGenrezuordnungen);
			
			Statement InsertStuff = conn.createStatement();
			//String ins = "INSERT INTO Filme (FilmID, Titel, Bewertung, Erscheinungsjahr) " +
			//			 "VALUES (1, 'Death Parade', 0.999, 2014) ";
			
			// InsertStuff.executeUpdate(ins);
			
			String myYear = "2014";
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Filme"); //WHERE Erscheinungsjahr like ?");
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String title = rs.getString("Titel");
				int year = rs.getInt("Erscheinungsjahr");
				System.out.println(title + "\t\t" + year);
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
