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
public class Prezzo {

    private Integer id_prezzo;
    private String tipo;
    private Float prezzo;

    public Prezzo() { //Il costruttore standard può essere tranquillamente vuoto
    }

    public Prezzo(Integer id_prezzo, String tipo, Float prezzo) {
        this.id_prezzo = id_prezzo;
        this.tipo = tipo;
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Prezzo[" + getId() + "," + getTipo() + "," + getPrezzo() + "]";
    }

    /**
     * @return the id_prezzo
     */
    public Integer getId() {
        return id_prezzo;
    }

    /**
     * @param id_prezzo the id_prezzo to set
     */
    public void setId(Integer id_prezzo) {
        this.id_prezzo = id_prezzo;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the prezzo
     */
    public Float getPrezzo() {
        return prezzo;
    }

    /**
     * @param prezzo the prezzo to set
     */
    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

}
