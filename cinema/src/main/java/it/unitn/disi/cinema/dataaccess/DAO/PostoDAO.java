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

import com.mysql.jdbc.NotImplemented;
import it.unitn.disi.cinema.dataaccess.Beans.Posto;
import it.unitn.disi.cinema.dataaccess.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Metodi disponibili per Posto:
 *
 * void addPosto(Posto posto) Posto getPostoById(Integer id) List<Posto>
 * getPostoBySalaId(Integer salaId) List<Posto> getAll() void updatePosto(Posto
 * posto) void deletePosto(Integer id)
 *
 * void setAvailability(Integer Id, Boolean available) Boolean
 * isAvailableForReservation(Posto posto) List<Boolean> getSalaMap(Integer
 * salaId)
 *
 * @author domenico
 */
public class PostoDAO {

    /*
        La classe Database è creata con il paradigma di singleton quindi ha costruttore privata e non può essere instanziata 
        nel programma.
        La sua unica istanza è una variabile statica al suo interno e può essere ottenuta con Database.getInstance();
     */

    Database db = Database.getInstance();   //Questa è l'istanza del database che serve per ottenere la connessione
    Connection conn = db.getConnection();

    public void addPosto(Posto posto) throws SQLException {
        PreparedStatement st = conn.prepareStatement("insert into Posto (id_sala,riga,poltrona,esiste) values (?,?,?,?)");
        st.setInt(1, posto.getSalaId());
        st.setInt(2, posto.getRiga());
        st.setInt(3, posto.getPoltrona());
        st.setBoolean(4, posto.getEsiste());

        st.executeUpdate();
    }

    public Posto getPostoById(Integer id) throws SQLException {
        Posto result;
        PreparedStatement st = conn.prepareStatement("select * from Posto where Posto.id_posto = ?");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = new Posto();
            result.setId(rs.getInt(1));
            result.setSalaId(rs.getInt(2));
            result.setRiga(rs.getInt(3));
            result.setPoltrona(rs.getInt(4));
            result.setEsiste(rs.getBoolean(5));

            if (rs.next()) {
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        } else {
            result = null;
        }

        return result;
    }

    public List<Posto> getPostoBySalaId(Integer salaId) throws SQLException {
        List<Posto> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("select * from Posto where Posto.id_sala = ? ORDER BY Posto.riga, Posto.poltrona");
        st.setInt(1, salaId);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Posto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5)));
        }

        return result;
    }

    public Posto getPostoBySalaRigaPoltrona(Integer salaId, Integer riga, Integer poltrona) throws SQLException {
        Posto result = null;

        PreparedStatement st = conn.prepareStatement("select * from Posto where Posto.id_sala = ? AND Posto.riga = ? AND Posto.poltrona = ?");
        st.setInt(1, salaId);
        st.setInt(2, riga);
        st.setInt(3, poltrona);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = new Posto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5));
            if (rs.next()) {
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        }

        return result;
    }

    public List<Posto> getAll() throws SQLException {    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Posto> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Posto");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Posto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5)));
        }
        return result;
    }

    void updatePosto(Posto posto) throws SQLException {
        throw new NotImplemented();
    }

    void deletePosto(Integer id) throws SQLException {

        PreparedStatement st = conn.prepareStatement("DELETE FROM Posto WHERE Posto.id_posto = ?");
        st.setInt(1, id);

        st.executeUpdate();
    }

    void setAvailability(Integer id, Boolean available) throws SQLException {
        PreparedStatement st;

        if (available == null) {
            throw new SQLException("Boolean parameter has to be set");
        }

        st = conn.prepareStatement("UPDATE Posto SET Posto.esiste=? WHERE Posto.id_posto=?");
        st.setBoolean(1, available);
        st.setInt(2, id);

        st.executeUpdate();
    }

    Boolean isAvailableForReservation(Integer id) throws SQLException {
        Boolean result = null;

        PreparedStatement st = conn.prepareStatement("select Posto.esiste from Posto where Posto.id_posto = ?");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = rs.getBoolean(1);
        }
        return result;
    }

    List<Boolean> getSalaMap(Integer salaId) throws SQLException {
        List<Boolean> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("select Posto.esiste from Posto where Posto.id_sala = ?");
        st.setInt(1, salaId);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(rs.getBoolean(1));
        }

        return result;
    }

    public Boolean isReserved(Integer idSpettacolo, Integer idPosto) throws SQLException {
        Boolean res = null;

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prenotazione WHERE Prenotazione.id_spettacolo = ? AND Prenotazione.id_posto = ?");
        st.setInt(1, idSpettacolo);
        st.setInt(2, idPosto);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            System.out.println("PRENOTAZIONE:" + rs.getInt(1));
            if (rs.next()) {

                System.out.println("PRENOTAZIONE:" + rs.getInt(1));
                throw new SQLException("Too much reservation for the same Spettacolo-Posto");
            } else {

                res = true;
            }
        } else {
            res = false;
        }

        return res;
    }
}
