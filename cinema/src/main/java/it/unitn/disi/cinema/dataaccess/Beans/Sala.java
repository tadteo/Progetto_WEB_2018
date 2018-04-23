/*
 * JavaBean Per l'elemento Utente
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
    public String toString(){
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
