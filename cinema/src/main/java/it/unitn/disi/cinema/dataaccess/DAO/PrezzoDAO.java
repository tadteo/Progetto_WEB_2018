package it.unitn.disi.cinema.dataaccess.DAO;

import it.unitn.disi.cinema.dataaccess.Beans.Prezzo;
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
 * getPrezzoById(Integer id)
 * getAll()
 * 
 * @author domenico
 */
public class PrezzoDAO {
    Database db = Database.getInstance();   
    Connection conn = db.getConnection();
    
       
    public Prezzo getPrezzoById (Integer id) throws SQLException{
        Prezzo result;
        PreparedStatement st = conn.prepareStatement("select * from Prezzo where Prezzo.id_prezzo = ?");
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        if(rs.next()){
            result = new Prezzo();
            result.setId(rs.getInt(1));
            result.setTipo(rs.getString(2));
            result.setPrezzo(rs.getFloat(3));
            
            if(rs.next()){
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        }else{
            result = null;
        }
        
        return result;
    }
    
    public List<Prezzo> getAll() throws SQLException{    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Prezzo> result = new ArrayList<>();
        
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prezzo");
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            result.add(new Prezzo(rs.getInt(1), rs.getString(2),rs.getFloat(3)));
        }
        return result;
    }
}
