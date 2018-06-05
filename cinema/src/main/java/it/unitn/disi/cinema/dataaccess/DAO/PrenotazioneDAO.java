/*
 * Questo è un DAO per la tabella Prenotazione
 * Quì vanno definiti dei metodi CRUD per potere accedere al db e gestire record della tabella user
 * Create, Retrieve, Update, Delete
 */
package it.unitn.disi.cinema.dataaccess.DAO;

import it.unitn.disi.cinema.dataaccess.Beans.Prenotazione;
import it.unitn.disi.cinema.dataaccess.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * CRUD Metodi disponibili per Prenotazione:
 *
 * void addPrenotazione(Prenotazione prenotazione)
 *
 * Prenotazione getPrenotazioneById(Integer id) List<Prenotazione> getAll() //In
 * futuro si potrebbe mettere parametro limit List<Prenotazione>
 * getAfter(Timestamp time) List<Prenotazione> getByUtente(Integer idUtente)
 * List<Prenotazione> getByUtenteAfter(Integer idUtente, Timestamp time)
 * List<Prenotazione> getByFilm(Integer idFilm) List<Prenotazione>
 * getByFIlmAfter(Integer idFilm, Timestamp time) List<Prenotazione>
 * getBySpettacolo(Integer idSpettacolo) List<Prenotazione>
 * getBySpettacoloAfter(Integer idSpettacolo, Timestamp time)
 *
 * //No update

 *
 * deletePrenotazione(Integer id);
 *
 *
 * @author domenico
 */
public class PrenotazioneDAO {

    Database db = Database.getInstance();   //Questa è l'istanza del database che serve per ottenere la connessione
    Connection conn = db.getConnection();

    public Boolean isItAlreadyStored(Integer idUtente, Integer idSpettacolo, Integer idPosto) throws SQLException {
        Boolean result = false;
        PreparedStatement st = conn.prepareStatement("select * from Prenotazione where id_utente = ? AND id_spettacolo = ? AND id_posto = ?;");
        st.setInt(1, idUtente);
        st.setInt(2, idSpettacolo);
        st.setInt(3, idPosto);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = true;
        }

        return result;
    }

    public void addPrenotazione(Prenotazione prenotazione) throws SQLException {
        PreparedStatement st = conn.prepareStatement("insert into Prenotazione (id_utente,id_spettacolo,id_prezzo,id_posto,data_ora_operazione) values (?,?,?,?,?)");
        st.setInt(1, prenotazione.getUtenteId());
        st.setInt(2, prenotazione.getSpettacoloId());
        st.setInt(3, prenotazione.getPrezzoId());
        st.setInt(4, prenotazione.getPostoId());
        st.setTimestamp(5, prenotazione.getDataOraOperazione());

        st.executeUpdate();
    }

    public Prenotazione getPrenotazioneById(Integer id) throws SQLException {
        Prenotazione result;
        PreparedStatement st = conn.prepareStatement("select * from Prenotazione where Prenotazione.id_prenotazione = ?");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = new Prenotazione();
            result.setId(rs.getInt(1));
            result.setUtenteId(rs.getInt(2));
            result.setSpettacoloId(rs.getInt(3));
            result.setPrezzoId(rs.getInt(4));
            result.setPostoId(rs.getInt(5));
            result.setDataOraOperazione(rs.getTimestamp(6));

            if (rs.next()) {
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        } else {
            result = null;
        }

        return result;
    }

    public List<Prenotazione> getAll() throws SQLException {    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Prenotazione> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prenotazione");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Prenotazione(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6)));
        }
        return result;
    }

    public List<Prenotazione> getAfter(Timestamp time) throws SQLException {
        List<Prenotazione> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prenotazione WHERE Prenotazione.data_ora_operazione > ?");
        st.setTimestamp(1, time);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Prenotazione(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6)));
        }
        return result;
    }

    public List<Prenotazione> getByUtente(Integer idUtente) throws SQLException {
        List<Prenotazione> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prenotazione WHERE Prenotazione.id_utente = ?");
        st.setInt(1, idUtente);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Prenotazione(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6)));
        }
        return result;
    }


    public List<Prenotazione> getByUtenteAfter(Integer idUtente, Timestamp time) throws SQLException {

        List<Prenotazione> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prenotazione WHERE Prenotazione.id_utente = ? AND Prenotazione.data_ora_operazione > ?");
        st.setInt(1, idUtente);
        st.setTimestamp(2, time);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Prenotazione(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6)));
        }
        return result;
    }

    public List<Prenotazione> getByFilm(Integer idFilm) throws SQLException {
        List<Prenotazione> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prenotazione WHERE Prenotazione.id_film = ?");
        st.setInt(1, idFilm);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Prenotazione(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6)));
        }
        return result;
    }

    public List<Prenotazione> getByFIlmAfter(Integer idFilm, Timestamp time) throws SQLException {
        List<Prenotazione> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prenotazione WHERE Prenotazione.id_film = ? AND Prenotazione.data_ora_operazione > ?");
        st.setInt(1, idFilm);
        st.setTimestamp(2, time);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Prenotazione(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6)));
        }
        return result;
    }

    public List<Prenotazione> getBySpettacolo(Integer idSpettacolo) throws SQLException {

        List<Prenotazione> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prenotazione WHERE Prenotazione.id_spettacolo = ?");
        st.setInt(1, idSpettacolo);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Integer id_prenotazione = rs.getInt(1);
            Integer id_utente = rs.getInt(2);
            Integer id_spettacolo = rs.getInt(3);
            Integer id_prezzo = rs.getInt(4);
            Integer id_posto = rs.getInt(5);
            Timestamp data_ora_operazione = rs.getTimestamp(6);
            result.add(new Prenotazione(id_prenotazione, id_utente, id_spettacolo, id_prezzo, id_posto, data_ora_operazione));
        }
        return result;
    }

    public List<Prenotazione> getBySpettacoloAfter(Integer idSpettacolo, Timestamp time) throws SQLException {
        List<Prenotazione> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Prenotazione WHERE Prenotazione.id_spettacolo = ? AND Prenotazione.data_ora_operazione > ?");
        st.setInt(1, idSpettacolo);
        st.setTimestamp(2, time);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Integer id_prenotazione = rs.getInt(1);
            Integer id_utente = rs.getInt(2);
            Integer id_spettacolo = rs.getInt(3);
            Integer id_prezzo = rs.getInt(4);
            Integer id_posto = rs.getInt(5);
            Timestamp data_ora_operazione = rs.getTimestamp(6);
            result.add(new Prenotazione(id_prenotazione, id_utente, id_spettacolo, id_prezzo, id_posto, data_ora_operazione));
        }
        return result;
    }

    public void deletePrenotazione(Integer id) throws SQLException {
        if (id == null) {
            throw new SQLException("Id paramter is null");
        }

        PreparedStatement st = conn.prepareStatement("DELETE FROM Prenotazione WHERE Prenotazione.id_prenotazione = ?");
        st.setInt(1, id);

        st.executeUpdate();
    }

}
