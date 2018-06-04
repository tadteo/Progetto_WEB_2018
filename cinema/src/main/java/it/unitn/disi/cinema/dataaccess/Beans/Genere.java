/*
 * Java Bean per l'elemento Genere
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
