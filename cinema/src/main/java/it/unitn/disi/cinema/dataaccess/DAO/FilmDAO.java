/*
 * Questo è un DAO per la tabella Film
 * Quì vanno definiti dei metodi CRUD per potere accedere al db e gestire record della tabella user
 * Create, Retrieve, Update, Delete
 */
package it.unitn.disi.cinema.dataaccess.DAO;

import it.unitn.disi.cinema.dataaccess.Beans.Film;
import it.unitn.disi.cinema.dataaccess.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Metodi disponibili per Film:
 * 
 * getFilmById(Integer id)
 * getAll() //In futuro si potrebbe mettere parametro limit
 * 
 * @author domenico
 */
public class FilmDAO {
    Database db = Database.getInstance();   //Questa è l'istanza del database che serve per ottenere la connessione
    Connection conn = db.getConnection();
    
     public Film getFilmById (Integer id) throws SQLException{
        Film result;
        PreparedStatement st = conn.prepareStatement("select * from Film where Film.id_film = ?");
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        if(rs.next()){
            result = new Film();
            result.setId(rs.getInt(1));
            result.setTitolo(rs.getString(2));
            result.setGenereId(rs.getInt(3));
            result.setUrlLocandina(rs.getString(4));
            result.setUrlTrailer(rs.getString(5));
            result.setDurata(rs.getInt(6));
            result.setTrama(rs.getString(7));
            
            if(rs.next()){
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        }else{
            result = null;
        }
        
        return result;
    }
    
    public List<Film> getAll() throws SQLException{    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Film> result = new ArrayList<>();
        
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Film");
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            result.add(new Film(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
        }
        return result;
    }
}
