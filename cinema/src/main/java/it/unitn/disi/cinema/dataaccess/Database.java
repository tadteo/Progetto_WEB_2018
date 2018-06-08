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

package it.unitn.disi.cinema.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Questa classe gestisce la connessione al Database. E' implementata con
 * paradigma singleton quindi non può essere instanziata dall'esterno. Viene
 * istanziata una singola volta alla sua creazione e la sua unica istanza può
 * essere ottenuta attraverso il metodo statico getInstance() L'obiettivo di
 * questa scelta è di permettere una sola connessione alla volta al database
 * attuale
 *
 * @author domenico
 */
public class Database {

    private Connection myConn;
    private static Database instance = new Database();     //Singleton

    private Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Questa riga è molto importante per comunicare a TomCat di includere la classe Driver del database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRORE: IMPOSSIBILE CONNETTERSI AL DATABASE");
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        return instance;
    }

    ;
    
    public Connection getConnection() {
        return myConn;
    }

}
