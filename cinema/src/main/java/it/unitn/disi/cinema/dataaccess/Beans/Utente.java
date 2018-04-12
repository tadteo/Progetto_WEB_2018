/*
 * JavaBean Per l'elemento Utente
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
    public String toString(){
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
