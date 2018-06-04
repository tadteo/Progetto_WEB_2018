/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
