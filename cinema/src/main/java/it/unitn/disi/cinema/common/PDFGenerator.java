/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    public static void generaPDF(String utente, ArrayList<String> qrCode, File path) throws IOException{
        //CREAZIONE DEL PDF
        try (PDDocument doc = new PDDocument()) {
            ArrayList<PDPage> page = new ArrayList<>();
            page.add( new PDPage());
            int counter = 0;
            doc.addPage(page.get(counter));
            
            ArrayList<PDPageContentStream> contentStream = new ArrayList<>();
            contentStream.add(new PDPageContentStream(doc, page.get(counter)));
            
            contentStream.get(counter).beginText();
            
            contentStream.get(counter).newLineAtOffset(30, 700);
            PDFont font = HELVETICA_BOLD;
            contentStream.get(counter).setFont(font, 24);
            contentStream.get(counter).showText("Biglietti Cinema World");
            contentStream.get(counter).endText();
            
            contentStream.get(counter).beginText();
            contentStream.get(counter).newLineAtOffset(30, 650);
            font = HELVETICA;
            contentStream.get(counter).setFont(font, 16);
            contentStream.get(counter).showText("Sotto puo' trovare tutti i codici QR corrispondenti ai biglietti da lei comprati: ");
            contentStream.get(counter).endText();
            contentStream.get(counter).close();

            for(String qr:qrCode){
                counter++;
                page.add(new PDPage());
                doc.addPage(page.get(counter));
                contentStream.add(new PDPageContentStream(doc, page.get(counter)));
                contentStream.get(counter).beginText();
            
                contentStream.get(counter).newLineAtOffset(30, 700);
                font = HELVETICA;
                contentStream.get(counter).setFont(font, 20);
                contentStream.get(counter).showText("Biglietto numero "+ counter);
                contentStream.get(counter).endText();
                PDImageXObject pdImage = PDImageXObject.createFromFile(qr, doc);
                contentStream.get(counter).drawImage(pdImage, 150, 200);
                contentStream.get(counter).close();        
            }
            
        
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
