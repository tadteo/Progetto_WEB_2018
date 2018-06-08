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
public class Genere {

    private Integer id_genere;
    private String descrizione;

    public Genere() { //Il costruttore standard può essere tranquillamente vuoto
    }

    public Genere(Integer id_genere, String descrizione) {
        this.id_genere = id_genere;
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Genere[" + getId() + "," + getDescrizione() + "]";
    }

    /**
     * @return the id_genere
     */
    public Integer getId() {
        return id_genere;
    }

    /**
     * @param id_genere the id_genere to set
     */
    public void setId(Integer id_genere) {
        this.id_genere = id_genere;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
