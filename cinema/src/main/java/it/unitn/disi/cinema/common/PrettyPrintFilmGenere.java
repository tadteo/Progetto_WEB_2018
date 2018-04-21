/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.common;

import it.unitn.disi.cinema.dataaccess.Beans.*;

/**
 *
 * @author domenico
 */
public class PrettyPrintFilmGenere {
    private Film film;
    private Genere genere;

    public PrettyPrintFilmGenere() {
    }

    public PrettyPrintFilmGenere(Film film, Genere genere) {
        this.film = film;
        this.genere = genere;
    }
    
    
    /**
     * @return the film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * @param film the film to set
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * @return the genere
     */
    public Genere getGenere() {
        return genere;
    }

    /**
     * @param genere the genere to set
     */
    public void setGenere(Genere genere) {
        this.genere = genere;
    }
    
}
