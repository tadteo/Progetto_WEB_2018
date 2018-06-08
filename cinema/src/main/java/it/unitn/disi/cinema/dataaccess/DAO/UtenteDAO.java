/*
 * Questo è un DAO per la tabella Users
 * Quì vanno definiti dei metodi CRUD per potere accedere al db e gestire record della tabella user
 * Create, Retrieve, Update, Delete

 * L'idea è di avere un DAO per ogni tabella o view che vogliamo gestire
 * In generale per ogni entità che vogliamo inserire o estrarre dal db
 * 

 * Il Bean (User.java) è solo l'oggetto di trasferimento dei dati
 */
package it.unitn.disi.cinema.dataaccess.DAO;

import it.unitn.disi.cinema.dataaccess.Beans.Utente;
import it.unitn.disi.cinema.dataaccess.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Metodi disponibili per Utente:
 *
 * addUtente(Utente utente) getUtenteByEmail(String email) getUtenteById(Integer
 * id) getAll() //In futuro si potrebbe mettere parametro limit
 * deleteUtente(String email) isUsed(String email)
 *
 * @author domenico
 */
public class UtenteDAO {

    /*
        La classe Database è creata con il paradigma di singleton quindi ha costruttore privata e non può essere instanziata 
        nel programma.
        La sua unica istanza è una variabile statica al suo interno e può essere ottenuta con Database.getInstance();
     */

    Database db = Database.getInstance();   //Questa è l'istanza del database che serve per ottenere la connessione
    Connection conn = db.getConnection();

    /**
     * CREATE (Crud)
     *
     * @param utente
     * @throws SQLException
     */
    public void addUtente(Utente utente) throws SQLException {
        PreparedStatement st = conn.prepareStatement("insert into Utente (id_ruolo,email,password,credito) values (?,?,?,?)");
        st.setInt(1, utente.getRuoloId());
        st.setString(2, utente.getEmail());
        st.setString(3, utente.getPassword());
        st.setFloat(4, utente.getCredito());

        st.executeUpdate();
    }

    public void updateUtente(Utente utente) throws SQLException{
        if((utente.getId() == null)||(utente.getEmail()== null))
            return;
        
        PreparedStatement st = conn.prepareStatement("UPDATE Utente\n" +
                                                    "SET id_ruolo = ?,\n" +
                                                    "    password = ?,\n" +
                                                    "    credito = ? \n" +
                                                    "WHERE id_utente=?");
        st.setInt(1, utente.getRuoloId());
        st.setString(2, utente.getPassword());
        st.setFloat(3, utente.getCredito());
        
        st.setInt(4, utente.getId());
        
        st.executeUpdate();
    }
    
    public Utente getUtenteByEmail(String email) throws SQLException {
        Utente result;
        PreparedStatement st = conn.prepareStatement("select * from Utente where Utente.email = ?");
        st.setString(1, email);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = new Utente();
            result.setId(rs.getInt(1));
            result.setRuoloId(rs.getInt(2));
            result.setEmail(rs.getString(3));
            result.setPassword(rs.getString(4));
            result.setCredito(rs.getFloat(5));

            if (rs.next()) {
                throw new SQLException("Entry duplicata per la stessa email");
            }
        } else {
            result = null;
        }

        return result;
    }

    public Utente getUtenteById(Integer id) throws SQLException {
        Utente result;
        
        if(id == null)
            return null;
        PreparedStatement st = conn.prepareStatement("select * from Utente where Utente.id_utente = ?");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = new Utente();
            result.setId(rs.getInt(1));
            result.setRuoloId(rs.getInt(2));
            result.setEmail(rs.getString(3));
            result.setPassword(rs.getString(4));
            result.setCredito(rs.getFloat(5));

            if (rs.next()) {
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        } else {
            result = null;
        }

        return result;
    }

    public List<Utente> getAll() throws SQLException {    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Utente> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Utente");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Utente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getFloat(5)));
        }
        return result;
    }

    public void deleteUtente(String email) throws SQLException {
        if (email == null) {
            throw new SQLException("Email nulla");
        }

        PreparedStatement st = conn.prepareStatement("DELETE FROM Utente WHERE email = ?");
        st.setString(1, email);

        st.executeUpdate();
    }

    public boolean isUsed(String email) throws SQLException {    //Funzione comoda per capire se esiste già un record 'username'
        Boolean result = null;

        if (email != null) {
            PreparedStatement st = conn.prepareStatement("SELECT true FROM Utente WHERE email = ?");
            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }
}
