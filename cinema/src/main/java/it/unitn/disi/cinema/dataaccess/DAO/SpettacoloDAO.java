/*
 * Cinema Universe - Reservation System
 * Copyright (C) 2018 Domenico Stefani, Ivan Martini, Matteo Tadiello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See <http://www.gnu.org/licenses/>.
 */

 /*
 * Questo è un DAO per la tabella Spettacolo
 * Quì vanno definiti dei metodi CRUD per potere accedere al db e gestire record della tabella user
 * Create, Retrieve, Update, Delete
 */
package it.unitn.disi.cinema.dataaccess.DAO;

import it.unitn.disi.cinema.dataaccess.Beans.Posto;
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
 * CRUD Metodi disponibili per Spettacolo:
 *
 * addSpettacolo(Spettacolo spettacolo)
 *
 * getSpettacoloById(Integer id) getAll() //In futuro si potrebbe mettere
 * parametro limit getAfter(Timestamp time) getByFilm(Integer idFilm)
 * getByFIlmAfter(Integer idFilm, Timestamp time)
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

    public void addSpettacolo(Spettacolo spettacolo) throws SQLException {
        PreparedStatement st = conn.prepareStatement("insert into Spettacolo (id_film,id_sala,data_ora) values (?,?,?)");
        st.setInt(1, spettacolo.getFilmId());
        st.setInt(2, spettacolo.getSalaId());
        st.setTimestamp(3, spettacolo.getDataOra());

        st.executeUpdate();
    }

    public Spettacolo getSpettacoloById(Integer id) throws SQLException {
        if (id == null || id == 0) {
            throw new SQLException("getSpettacoloById was called with a null or 0 argument");
        }
        Spettacolo result;
        PreparedStatement st = conn.prepareStatement("select * from Spettacolo where Spettacolo.id_spettacolo = ?");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = new Spettacolo();
            result.setId(rs.getInt(1));
            result.setFilmId(rs.getInt(2));
            result.setSalaId(rs.getInt(3));
            result.setDataOra(rs.getTimestamp(4));

            if (rs.next()) {
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        } else {
            result = null;
        }

        return result;
    }

    public List<Spettacolo> getAll() throws SQLException {    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Spettacolo> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Spettacolo ORDER BY Spettacolo.data_ora");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Spettacolo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4)));
        }
        return result;
    }

    public List<Spettacolo> getAfter(Timestamp time) throws SQLException {    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Spettacolo> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Spettacolo WHERE Spettacolo.data_ora > ?  ORDER BY Spettacolo.data_ora");
        st.setTimestamp(1, time);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Spettacolo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4)));
        }
        return result;
    }

    public List<Spettacolo> getByFilm(Integer idFilm) throws SQLException {    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Spettacolo> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Spettacolo WHERE Spettacolo.id_film = ? ORDER BY Spettacolo.data_ora");
        st.setInt(1, idFilm);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Spettacolo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4)));
        }
        return result;
    }

    public List<Spettacolo> getByFIlmAfter(Integer idFilm, Timestamp time) throws SQLException {    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Spettacolo> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Spettacolo WHERE Spettacolo.id_film = ? AND Spettacolo.data_ora > ? ORDER BY Spettacolo.data_ora");
        st.setInt(1, idFilm);
        st.setTimestamp(2, time);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Spettacolo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4)));
        }
        return result;
    }

    public void deleteSpettacolo(Integer id) throws SQLException {
        if (id == null) {
            throw new SQLException("Id patamter is null");
        }

        PreparedStatement st = conn.prepareStatement("DELETE FROM Spettacolo WHERE Spettacolo.id_spettacolo = ?");
        st.setInt(1, id);

        st.executeUpdate();
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
            throw new SQLException("Impossibile ottenere il conteggio dei posti disponibili per lo spettacolo");
        }

        return res;
    }

    public List<Posto> getReservedPosti(Integer id) throws SQLException {
        List<Posto> res = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT P.id_posto, P.id_sala, P.riga, P.poltrona, P.esiste "
                + "FROM Spettacolo AS S,Posto AS P, Prenotazione AS R "
                + "WHERE  "
                + "S.id_spettacolo = ? AND "
                + "S.id_spettacolo = R.id_spettacolo AND "
                + "S.id_sala = P.id_sala AND "
                + "P.id_posto = R.id_posto AND "
                + "P.esiste ");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        Posto currentPosto;
        while (rs.next()) {
            currentPosto = new Posto();
            currentPosto.setId(rs.getInt(1));
            currentPosto.setSalaId(rs.getInt(2));
            currentPosto.setRiga(rs.getInt(3));
            currentPosto.setPoltrona(rs.getInt(4));
            currentPosto.setEsiste(rs.getBoolean(5));
            res.add(currentPosto);
        }

        return res;
    }
}
