/*
 * JavaBean Per l'elemento Utente
 */
package it.unitn.disi.cinema.dataaccess.Beans;


/**
 *
 * @author domenico
 */
public class PackagePosto {

    private Posto posto;
    private Prenotazione prenotazione;

    public PackagePosto(Posto posto, Prenotazione prenotazione) {
        this.posto = posto;
        this.prenotazione = prenotazione;
    }
    
    /**
     * @return the posto
     */
    public Posto getPosto() {
        return posto;
    }

    /**
     * @param posto the posto to set
     */
    public void setPosto(Posto posto) {
        this.posto = posto;
    }

    /**
     * @return the prenotazione
     */
    public Prenotazione getPrenotazione() {
        return prenotazione;
    }

    /**
     * @param prenotazione the prenotazione to set
     */
    public void setPrenotazione(Prenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }

    
}
