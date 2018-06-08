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
public class Prenotazione {

    private Integer id_prenotazione;
    private Integer id_utente;
    private Integer id_spettacolo;
    private Integer id_prezzo;
    private Integer id_posto;
    private Timestamp data_ora_operazione;

    public Prenotazione() { //Il costruttore standard può essere tranquillamente vuoto
    }

    public Prenotazione(Integer id_prenotazione, Integer id_utente, Integer id_spettacolo, Integer id_prezzo, Integer id_posto, Timestamp data_ora_operazione) {
        this.id_prenotazione = id_prenotazione;
        this.id_utente = id_utente;
        this.id_spettacolo = id_spettacolo;
        this.id_prezzo = id_prezzo;
        this.id_posto = id_posto;
        this.data_ora_operazione = data_ora_operazione;
    }

    @Override
    public String toString() {
        return "Prenotazione[" + getId() + "," + getUtenteId() + "," + getSpettacoloId() + "," + getPrezzoId() + "," + getPostoId() + "," + getDataOraOperazione() + "]";
    }

    /**
     * @return the id_prenotazione
     */
    public Integer getId() {
        return id_prenotazione;
    }

    /**
     * @param id_prenotazione the id_prenotazione to set
     */
    public void setId(Integer id_prenotazione) {
        this.id_prenotazione = id_prenotazione;
    }

    /**
     * @return the id_utente
     */
    public Integer getUtenteId() {
        return id_utente;
    }

    /**
     * @param id_utente the id_utente to set
     */
    public void setUtenteId(Integer id_utente) {
        this.id_utente = id_utente;
    }

    /**
     * @return the id_spettacolo
     */
    public Integer getSpettacoloId() {
        return id_spettacolo;
    }

    /**
     * @param id_spettacolo the id_spettacolo to set
     */
    public void setSpettacoloId(Integer id_spettacolo) {
        this.id_spettacolo = id_spettacolo;
    }

    /**
     * @return the id_prezzo
     */
    public Integer getPrezzoId() {
        return id_prezzo;
    }

    /**
     * @param id_prezzo the id_prezzo to set
     */
    public void setPrezzoId(Integer id_prezzo) {
        this.id_prezzo = id_prezzo;
    }

    /**
     * @return the id_posto
     */
    public Integer getPostoId() {
        return id_posto;
    }

    /**
     * @param id_posto the id_posto to set
     */
    public void setPostoId(Integer id_posto) {
        this.id_posto = id_posto;
    }

    /**
     * @return the data_ora_operazione
     */
    public Timestamp getDataOraOperazione() {
        return data_ora_operazione;
    }

    /**
     * @param data_ora_operazione the data_ora_operazione to set
     */
    public void setDataOraOperazione(Timestamp data_ora_operazione) {
        this.data_ora_operazione = data_ora_operazione;
    }
}
