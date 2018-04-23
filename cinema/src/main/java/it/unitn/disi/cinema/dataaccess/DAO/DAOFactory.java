package it.unitn.disi.cinema.dataaccess.DAO;

/**
 *  La daoFactory è una semplice classe che crea istanze dei dao esistenti
 * @author domenico
 */
public class DAOFactory {
    public static UtenteDAO getUtenteDAO(){
        return new UtenteDAO();
    }
    public static RuoloDAO getRuoloDAO(){
        return new RuoloDAO();
    }
    public static GenereDAO getGenereDAO(){
        return new GenereDAO();
    }
    public static FilmDAO getFilmDAO(){
        return new FilmDAO();
    }
//    public static SpettacoloDAO getSpettacoloDAO(){
//        return new SpettacoloDAO();
//    }
//    public static SalaDAO getSalaDAO(){
//        return new SalaDAO();
//    }
//    public static PrenotazioneDAO getPrenotazioneDAO(){
//        return new PrenotazioneDAO();
//    }
    public static PrezzoDAO getPrezzoDAO(){
        return new PrezzoDAO();
    }
    public static PostoDAO getPostoDAO(){
        return new PostoDAO();
    }
}
    
