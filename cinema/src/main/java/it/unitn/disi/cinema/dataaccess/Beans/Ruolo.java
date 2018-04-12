/*
 * Java Bean per l'elemento Ruolo
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
    public String toString(){
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
