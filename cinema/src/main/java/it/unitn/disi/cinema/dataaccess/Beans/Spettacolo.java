/*
 * JavaBean Per l'elemento Utente
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
    public String toString(){
        return "Spettacolo[" + getId() + "," + getFilmId()+ "," + getSalaId() + "," + getDataOra() + "]";
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
