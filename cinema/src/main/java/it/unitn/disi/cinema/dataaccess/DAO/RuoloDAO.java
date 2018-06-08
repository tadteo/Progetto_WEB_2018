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
 * getRuoloById(Integer id) getAll()
 *
 * @author domenico
 */
public class RuoloDAO {

    Database db = Database.getInstance();
    Connection conn = db.getConnection();

    public Ruolo getRuoloById(Integer id) throws SQLException {
        Ruolo result;
        PreparedStatement st = conn.prepareStatement("select * from Ruolo where Ruolo.id_ruolo = ?");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            result = new Ruolo();
            result.setId(rs.getInt(1));
            result.setRuolo(rs.getString(2));

            if (rs.next()) {
                throw new SQLException("Result Set contiene più di un risultato per la stessa chiave primaria");
            }
        } else {
            result = null;
        }

        return result;
    }

    public List<Ruolo> getAll() throws SQLException {    //Non consigliato per tabelle grandi, conviene mettere un LIMIT per prendere pochi record
        List<Ruolo> result = new ArrayList<>();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM Ruolo");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result.add(new Ruolo(rs.getInt(1), rs.getString(2)));
        }
        return result;
    }
}
