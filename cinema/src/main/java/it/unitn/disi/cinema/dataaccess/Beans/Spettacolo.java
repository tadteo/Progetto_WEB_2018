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

import java.sql.Timestamp;

/**
 *
 * @author domenico
 */
public class Spettacolo {

    private Integer id_spettacolo;
    private Integer id_film;
    private Integer id_sala;
    private Timestamp data_ora;

    public Spettacolo() { //Il costruttore standard può essere tranquillamente vuoto
    }

    public Spettacolo(Integer id_spettacolo, Integer id_film, Integer id_sala, Timestamp data_ora) {
        this.id_spettacolo = id_spettacolo;
        this.id_film = id_film;
        this.id_sala = id_sala;
        this.data_ora = data_ora;
    }

    @Override
    public String toString() {
        return "Spettacolo[" + getId() + "," + getFilmId() + "," + getSalaId() + "," + getDataOra() + "]";
    }

    /**
     * @return the id_spettacolo
     */
    public Integer getId() {
        return id_spettacolo;
    }

    /**
     * @param id_spettacolo the id_spettacolo to set
     */
    public void setId(Integer id_spettacolo) {
        this.id_spettacolo = id_spettacolo;
    }

    /**
     * @return the id_film
     */
    public Integer getFilmId() {
        return id_film;
    }

    /**
     * @param id_film the id_film to set
     */
    public void setFilmId(Integer id_film) {
        this.id_film = id_film;
    }

    /**
     * @return the id_sala
     */
    public Integer getSalaId() {
        return id_sala;
    }

    /**
     * @param id_sala the id_sala to set
     */
    public void setSalaId(Integer id_sala) {
        this.id_sala = id_sala;
    }

    /**
     * @return the data_ora
     */
    public Timestamp getDataOra() {
        return data_ora;
    }

    /**
     * @param data_ora the data_ora to set
     */
    public void setDataOra(Timestamp data_ora) {
        this.data_ora = data_ora;
    }

}
