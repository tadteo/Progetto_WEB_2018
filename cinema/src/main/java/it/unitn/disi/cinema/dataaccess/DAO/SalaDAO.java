/*
 * Questo è un DAO per la tabella Sala
 * Quì vanno definiti dei metodi CRUD per potere accedere al db e gestire record della tabella user
 * Create, Retrieve, Update, Delete

 * L'idea è di avere un DAO per ogni tabella o view che vogliamo gestire
 * In generale per ogni entità che vogliamo inserire o estrarre dal db
 * 

 * Il Bean (User.java) è solo l'oggetto di trasferimento dei dati
 */
package it.unitn.disi.cinema.dataaccess.DAO;

import it.unitn.disi.cinema.dataaccess.Beans.Sala;
import it.unitn.disi.cinema.dataaccess.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Metodi disponibili per Sala: CRUD addSala(Sala sala)
 *
 * getSalaById(Integer id) getAll()
 *
 * updateSala(Sala sala)
 *
 * deleteSala
 *
 * @author domenico
 */
public class SalaDAO {

    /*
        La classe Database è creata con il paradigma di singleton quindi ha costruttore privata e non può essere instanziata 
        nel programma.
        La sua unica istanza è una variabile statica al suo interno e può essere ottenuta con Database.getInstance();
     */

    Database db = Database.getInstance();   //Questa è l'istanza del database che serve per ottenere la connessione
    Connection conn = db.getConnection();

    public void addSala(Sala sala) throws SQLException {
        PreparedStatement st = conn.prepareStatement("insert into Sala (descrizione) values (?)");
        st.setString(1, sala.getDescrizione());

        st.executeUpdate();
    }

    public Sala getSalaById(Integer id) throws SQLException {
        Sala result;
        PreparedStatement st = conn.prepareStatement("select * from Sala where Sala.id_sala = ?");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = new Sala(rs.getInt(1), rs.getString(2));

            if (rs.next()) {
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        } else {
            result = null;
        }

        return result;
    }

    public List<Sala> getAll() throws SQLException {    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Sala> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Sala");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Sala(rs.getInt(1), rs.getString(2)));
        }
        return result;
    }

    public void deleteSala(Integer id) throws SQLException {

        PreparedStatement st = conn.prepareStatement("DELETE FROM Sala WHERE Sala.id_sala = ?");
        st.setInt(1, id);

        st.executeUpdate();
    }

    public int getPostiCount(Integer id) throws SQLException {
        int res = -1;

        PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM Posto WHERE (Posto.id_sala = ?) AND (Posto.esiste = true)");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            res = rs.getInt(1);
        } else {
            throw new SQLException("Impossibile ottenere il conteggio dei posti");
        }

        return res;
    }

    public int getAvailablePostiCount(Integer id) throws SQLException {
        int res = -1;

        PreparedStatement st = conn.prepareStatement("SELECT count(*) "
                + "FROM Spettacolo AS S,Posto AS P, Prenotazione AS R "
                + "WHERE  "
                + "S.id_spettacolo = ? AND "
                + "S.id_spettacolo = R.id_spettacolo AND "
                + "S.id_sala = P.id_sala AND "
                + "P.id_posto = R.id_posto AND "
                + "P.esiste ");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            res = rs.getInt(1);
        } else {
            throw new SQLException("Impossibile ottenere il conteggio dei posti");
        }

        return res;
    }
}
