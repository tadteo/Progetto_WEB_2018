/*
 * Questo è un DAO per la tabella Spettacolo
 * Quì vanno definiti dei metodi CRUD per potere accedere al db e gestire record della tabella user
 * Create, Retrieve, Update, Delete
 */
package it.unitn.disi.cinema.dataaccess.DAO;

import it.unitn.disi.cinema.dataaccess.Beans.Spettacolo;
import it.unitn.disi.cinema.dataaccess.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 * CRUD
 * Metodi disponibili per Spettacolo:
 * 
 addSpettacolo(Spettacolo spettacolo)
 * 
 getSpettacoloById(Integer id)
 getAll() //In futuro si potrebbe mettere parametro limit
 getAfter(Timestamp time)
 getByFilm(Integer idFilm)
 getByFIlmAfter(Integer idFilm, Timestamp time)
 * 
 * //No update
 * 
 * deleteSpettacolo(Integer id);
 * 
 * 
 * @author domenico
 */
public class SpettacoloDAO {
    Database db = Database.getInstance();   //Questa è l'istanza del database che serve per ottenere la connessione
    Connection conn = db.getConnection();
    
    public void addSpettacolo(Spettacolo spettacolo) throws SQLException{
        PreparedStatement st = conn.prepareStatement("insert into Spettacolo (id_film,id_sala,dataora) values (?,?,?)");
        st.setInt(1, spettacolo.getFilmId());
        st.setInt(2, spettacolo.getSalaId());
        st.setTimestamp(3, spettacolo.getDataOra());
        
        st.executeUpdate();
    }
    
    public Spettacolo getSpettacoloById(Integer id) throws SQLException{
        Spettacolo result;
        PreparedStatement st = conn.prepareStatement("select * from Spettacolo where Spettacolo.id_spettacolo = ?");
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        if(rs.next()){
            result = new Spettacolo();
            result.setId(rs.getInt(1));
            result.setFilmId(rs.getInt(2));
            result.setSalaId(rs.getInt(3));
            result.setDataOra(rs.getTimestamp(4));
            
            if(rs.next()){
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        }else{
            result = null;
        }
        
        return result;
    }
    
    public List<Spettacolo> getAll() throws SQLException{    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Spettacolo> result = new ArrayList<>();
        
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Spettacolo");
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            result.add(new Spettacolo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4)));
        }
        return result;
    }
    
    public List<Spettacolo> getAfter(Timestamp time)throws SQLException{    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Spettacolo> result = new ArrayList<>();
        
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Spettacolo WHERE Spettacolo.dataora > ?");
        st.setTimestamp(1, time);
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            result.add(new Spettacolo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4)));
        }
        return result;
    }
    
    public List<Spettacolo> getByFilm(Integer idFilm) throws SQLException{    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Spettacolo> result = new ArrayList<>();
        
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Spettacolo WHERE Spettacolo.id_film = ?");
        st.setInt(1, idFilm);
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            result.add(new Spettacolo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4)));
        }
        return result;
    }
    public List<Spettacolo> getByFIlmAfter(Integer idFilm, Timestamp time) throws SQLException{    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Spettacolo> result = new ArrayList<>();
        
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Spettacolo WHERE Spettacolo.id_film = ? AND Spettacolo.dataora > ?");
        st.setInt(1, idFilm);
        st.setTimestamp(2, time);
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            result.add(new Spettacolo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4)));
        }
        return result;
    }
    
    public void deleteSpettacolo(Integer id) throws SQLException{
        if(id == null)
            throw new SQLException("Id patamter is null");
        
        PreparedStatement st = conn.prepareStatement("DELETE FROM Spettacolo WHERE Spettacolo.id_spettacolo = ?");
        st.setInt(1, id);
        
        st.executeUpdate();
    }
}
