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
public class Posto {

    private Integer id_posto;
    private Integer id_sala;
    private Integer riga;
    private Integer poltrona;
    private Boolean esiste;

    public Posto() { //Il costruttore standard può essere tranquillamente vuoto
    }

    public Posto(Integer id_posto, Integer id_sala, Integer riga, Integer poltrona, Boolean esiste) {
        this.id_posto = id_posto;
        this.id_sala = id_sala;
        this.riga = riga;
        this.poltrona = poltrona;
        this.esiste = esiste;
    }

    @Override
    public String toString() {
        return "Posto[" + getId() + "," + getSalaId() + "," + getRiga() + "," + getPoltrona() + "," + getEsiste() + "]";
    }

    /**
     * @return the id_posto
     */
    public Integer getId() {
        return id_posto;
    }

    /**
     * @param id_posto the id_posto to set
     */
    public void setId(Integer id_posto) {
        this.id_posto = id_posto;
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
     * @return the riga
     */
    public Integer getRiga() {
        return riga;
    }

    /**
     * @param riga the riga to set
     */
    public void setRiga(Integer riga) {
        this.riga = riga;
    }

    /**
     * @return the poltrona
     */
    public Integer getPoltrona() {
        return poltrona;
    }

    /**
     * @param poltrona the poltrona to set
     */
    public void setPoltrona(Integer poltrona) {
        this.poltrona = poltrona;
    }

    /**
     * @return the esiste
     */
    public Boolean getEsiste() {
        return esiste;
    }

    /**
     * @param esiste the esiste to set
     */
    public void setEsiste(Boolean esiste) {
        this.esiste = esiste;
    }

}
