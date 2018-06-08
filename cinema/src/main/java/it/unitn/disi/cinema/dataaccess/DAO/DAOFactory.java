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

/**
 * La daoFactory è una semplice classe che crea istanze dei dao esistenti
 *
 * @author domenico
 */
public class DAOFactory {

    public static UtenteDAO getUtenteDAO() {
        return new UtenteDAO();
    }

    public static RuoloDAO getRuoloDAO() {
        return new RuoloDAO();
    }

    public static GenereDAO getGenereDAO() {
        return new GenereDAO();
    }

    public static FilmDAO getFilmDAO() {
        return new FilmDAO();
    }

    public static SpettacoloDAO getSpettacoloDAO() {
        return new SpettacoloDAO();
    }

    public static SalaDAO getSalaDAO() {
        return new SalaDAO();
    }

    public static PrenotazioneDAO getPrenotazioneDAO() {
        return new PrenotazioneDAO();
    }

    public static PrezzoDAO getPrezzoDAO() {
        return new PrezzoDAO();
    }

    public static PostoDAO getPostoDAO() {
        return new PostoDAO();
    }
}
