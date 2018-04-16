package it.unitn.disi.cinema.dataaccess.DAO;

import it.unitn.disi.cinema.dataaccess.Beans.Ruolo;
import it.unitn.disi.cinema.dataaccess.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Metodi disponibili per Ruolo:
 * 
 * getRuoloById(Integer id)
 * getAll()
 * 
 * @author domenico
 */
public class RuoloDAO {
    Database db = Database.getInstance();   
    Connection conn = db.getConnection();
    
       
    public Ruolo getRuoloById (Integer id) throws SQLException{
        Ruolo result;
        PreparedStatement st = conn.prepareStatement("select * from Ruolo where Ruolo.id_ruolo = ?");
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        if(rs.next()){
            result = new Ruolo();
            result.setId(rs.getInt(1));
            result.setRuolo(rs.getString(2));
            
            if(rs.next()){
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        }else{
            result = null;
        }
        
        return result;
    }
    
    public List<Ruolo> getAll() throws SQLException{    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Ruolo> result = new ArrayList<>();
        
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Ruolo");
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            result.add(new Ruolo(rs.getInt(1), rs.getString(2)));
        }
        return result;
    }
}
