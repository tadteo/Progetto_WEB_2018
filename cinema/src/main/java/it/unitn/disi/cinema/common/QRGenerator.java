/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.common;

import it.unitn.disi.cinema.dataaccess.Beans.Spettacolo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author matteo
 */
public class QRGenerator {
    
    public static void generaQR(String path, String utente, String prezzo, String tipoBiglietto, String posto, Spettacolo spettacolo) throws IOException{
        String qrInformation = "";
        qrInformation += "Comprato da "+utente+"\nPosto: "+posto+"\nTipo: "+tipoBiglietto+"\nCosto: "+prezzo+"\nSpettacolo: "+spettacolo.getId()+"\nData-Ora: "+spettacolo.getDataOra();
        ByteArrayOutputStream imageStream = QRCode.from(qrInformation).to(ImageType.PNG).stream();             
          
            FileOutputStream outStream = new FileOutputStream(path);
            outStream.write(imageStream.toByteArray());
            outStream.flush(); 
            outStream.close();
    }
}
