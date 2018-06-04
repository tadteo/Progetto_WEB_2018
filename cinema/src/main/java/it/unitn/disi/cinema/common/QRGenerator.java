package it.unitn.disi.cinema.common;

import it.unitn.disi.cinema.dataaccess.Beans.Spettacolo;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author matteo
 */
public class QRGenerator {

    public static void generaQR(String path, String utente, String prezzo, String tipoBiglietto, String posto, Spettacolo spettacolo) throws IOException {

        //Creazione della stringa memorizzata nel qrcode
        String qrInformation = "";
        qrInformation += "Comprato da " + utente + "\nPosto: " + posto + "\nTipo: " + tipoBiglietto + "\nCosto: " + prezzo + "\nSpettacolo: " + spettacolo.getId() + "\nData-Ora: " + spettacolo.getDataOra();

        //Creazione del qrcode e salvataggio nel file
        ByteArrayOutputStream imageStream = QRCode.from(qrInformation).to(ImageType.PNG).withSize(250, 250).stream();
        FileOutputStream outStream = new FileOutputStream(path);
        outStream.write(imageStream.toByteArray());
        outStream.flush();
        outStream.close();
    }
}
