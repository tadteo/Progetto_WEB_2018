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
public class Sala {

    private Integer id_sala;
    private String descrizione;

    public Sala() { //Il costruttore standard può essere tranquillamente vuoto
    }

    public Sala(Integer id_sala, String descrizione) {
        this.id_sala = id_sala;
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Sala[" + getId() + "," + getDescrizione() + "]";
    }

    /**
     * @return the id_sala
     */
    public Integer getId() {
        return id_sala;
    }

    /**
     * @param id_sala the id_sala to set
     */
    public void setId(Integer id_sala) {
        this.id_sala = id_sala;
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
