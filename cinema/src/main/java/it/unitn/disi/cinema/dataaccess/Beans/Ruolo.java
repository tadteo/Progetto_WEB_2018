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
public class Ruolo {

    private Integer id_ruolo;
    private String ruolo;

    public Ruolo() { //Il costruttore standard può essere tranquillamente vuoto
    }

    public Ruolo(Integer id_ruolo, String ruolo) {
        this.id_ruolo = id_ruolo;
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "Ruolo[" + getId() + "," + getRuolo() + "]";
    }

    /**
     * @return the id_ruolo
     */
    public Integer getId() {
        return id_ruolo;
    }

    /**
     * @param id_ruolo the id_ruolo to set
     */
    public void setId(Integer id_ruolo) {
        this.id_ruolo = id_ruolo;
    }

    /**
     * @return the ruolo
     */
    public String getRuolo() {
        return ruolo;
    }

    /**
     * @param ruolo the ruolo to set
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
