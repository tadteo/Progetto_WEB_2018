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
public class Utente {

    private Integer id_utente;
    private Integer id_ruolo;
    private String email;
    private String password;
    private Float credito;

    public Utente() { //Il costruttore standard può essere tranquillamente vuoto
    }

    public Utente(Integer id_utente, Integer id_ruolo, String email, String password, Float credito) {
        this.id_utente = id_utente;
        this.id_ruolo = id_ruolo;
        this.email = email;
        this.password = password;
        this.credito = credito;
    }

    @Override
    public String toString() {
        return "Utente[" + getId() + "," + getRuoloId() + "," + getEmail() + "," + getPassword() + "," + getCredito() + "]";
    }

    /**
     * @return the id_utente
     */
    public Integer getId() {
        return id_utente;
    }

    /**
     * @param id_utente the id_utente to set
     */
    public void setId(Integer id_utente) {
        this.id_utente = id_utente;
    }

    /**
     * @return the id_ruolo
     */
    public Integer getRuoloId() {
        return id_ruolo;
    }

    /**
     * @param id_ruolo the id_ruolo to set
     */
    public void setRuoloId(Integer id_ruolo) {
        this.id_ruolo = id_ruolo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the credito
     */
    public Float getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(Float credito) {
        this.credito = credito;
    }

}
