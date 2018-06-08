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
package it.unitn.disi.cinema.dataaccess.Beans;

/**
 *
 * @author domenico
 */
public class Film {

    private Integer id_film;
    private String titolo;
    private Integer id_genere;
    private String url_locandina;
    private String url_trailer;
    private Integer durata;
    private String trama;

    public Film() { //Il costruttore standard può essere tranquillamente vuoto
    }

    public Film(Integer id_film, String titolo, Integer id_genere, String url_locandina, String url_trailer, Integer durata, String trama) {
        this.id_film = id_film;
        this.titolo = titolo;
        this.id_genere = id_genere;
        this.url_locandina = url_locandina;
        this.url_trailer = url_trailer;
        this.durata = durata;
        this.trama = trama;
    }

    @Override
    public String toString() {
        return "Film[" + getId() + "," + getTitolo() + "," + getGenereId() + "," + getUrlLocandina() + "," + getUrlTrailer() + "," + getDurata() + "," + "<TramaOmessa>" + "]";
    }

    /**
     * @return the id_film
     */
    public Integer getId() {
        return id_film;
    }

    /**
     * @param id_film the id_film to set
     */
    public void setId(Integer id_film) {
        this.id_film = id_film;
    }

    /**
     * @return the titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @param titolo the titolo to set
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @return the id_genere
     */
    public Integer getGenereId() {
        return id_genere;
    }

    /**
     * @param id_genere the id_genere to set
     */
    public void setGenereId(Integer id_genere) {
        this.id_genere = id_genere;
    }

    /**
     * @return the url_locandina
     */
    public String getUrlLocandina() {
        return url_locandina;
    }

    /**
     * @param url_locandina the url_locandina to set
     */
    public void setUrlLocandina(String url_locandina) {
        this.url_locandina = url_locandina;
    }

    /**
     * @return the url_trailer
     */
    public String getUrlTrailer() {
        return url_trailer;
    }

    /**
     * @param url_trailer the url_trailer to set
     */
    public void setUrlTrailer(String url_trailer) {
        this.url_trailer = url_trailer;
    }

    /**
     * @return the durata
     */
    public Integer getDurata() {
        return durata;
    }

    /**
     * @param durata the durata to set
     */
    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    /**
     * @return the trama
     */
    public String getTrama() {
        return trama;
    }

    /**
     * @param trama the trama to set
     */
    public void setTrama(String trama) {
        this.trama = trama;
    }
}
