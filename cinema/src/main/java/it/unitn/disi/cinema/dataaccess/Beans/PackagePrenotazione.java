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
public class PackagePrenotazione {

    private Integer prenotazione;
    private String titoloFilm;
    private Timestamp oraPrenotazione;
    private Timestamp oraSpettacolo;
    private String posto;

    public PackagePrenotazione(Integer prenotazione, String titolo_film, Timestamp ora_prenotazione, Timestamp ora_spettacolo, String posto) {
        this.prenotazione = prenotazione;
        this.titoloFilm = titolo_film;
        this.oraPrenotazione = ora_prenotazione;
        this.oraSpettacolo = ora_spettacolo;
        this.posto = posto;
    }

    /**
     * @return the prenotazione
     */
    public Integer getPrenotazione() {
        return prenotazione;
    }

    /**
     * @param prenotazione the prenotazione to set
     */
    public void setPrenotazione(Integer prenotazione) {
        this.prenotazione = prenotazione;
    }

    /**
     * @return the posto
     */
    public String getPosto() {
        return posto;
    }

    /**
     * @return the titoloFilm
     */
    public String getTitoloFilm() {
        return titoloFilm;
    }

    /**
     * @param titoloFilm the titoloFilm to set
     */
    public void setTitoloFilm(String titoloFilm) {
        this.titoloFilm = titoloFilm;
    }

    /**
     * @return the oraPrenotazione
     */
    public Timestamp getOraPrenotazione() {
        return oraPrenotazione;
    }

    /**
     * @param oraPrenotazione the oraPrenotazione to set
     */
    public void setOraPrenotazione(Timestamp oraPrenotazione) {
        this.oraPrenotazione = oraPrenotazione;
    }

    /**
     * @return the oraSpettacolo
     */
    public Timestamp getOraSpettacolo() {
        return oraSpettacolo;
    }

    /**
     * @param oraSpettacolo the oraSpettacolo to set
     */
    public void setOraSpettacolo(Timestamp oraSpettacolo) {
        this.oraSpettacolo = oraSpettacolo;
    }

    /**
     * @param posto the posto to set
     */
    public void setPosto(String posto) {
        this.posto = posto;
    }

}
