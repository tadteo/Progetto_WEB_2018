/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.common;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA_BOLD;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author matteo
 */
public class PDFGenerator {
    public static void generaPDF(String utente, File[] qrCode, File path) throws IOException{
        //CREAZIONE DEL PDF
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);
            
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            
            contentStream.beginText();
            
            contentStream.newLineAtOffset(25, 700);
            PDFont font = HELVETICA_BOLD;
            contentStream.setFont(font, 24);
            contentStream.showText("Biglietti Cinema World");
            
            contentStream.newLineAtOffset(25, 680);
            font = HELVETICA;
            contentStream.setFont(font, 24);
            contentStream.showText("Sotto puo' trovare tutti i codici QR corrispondenti ai biglietti da lei comprati: ");
            contentStream.endText();

            for(File qr:qrCode){
                PDImageXObject pdImage = PDImageXObject.createFromFileByContent(qr, doc);
                contentStream.drawImage(pdImage, 250, 250);
            }
            
            contentStream.close();
        
            doc.save(path); 
            doc.close();

//            String ext = "png";
//            String name = String.format("%s.%s",RandomStringUtils.randomAlphanumeric(8), ext);
//            response.setContentType("image/png");
//            response.addHeader("Content-Disposition", "filename="+request.getContextPath()+name);
//            response.getOutputStream().write(imageStream.toByteArray());
//            
//            
        }
       
    }
}
