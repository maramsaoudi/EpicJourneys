/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;  
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.stage.FileChooser;
//import javax.swing.text.Document;
import services.UserCRUD;



import com.itextpdf.text.Document;  // Importez Document de com.itextpdf.text
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import services.UserCRUD;

/**
 *
 * @author ghofr
 */
public class Pdfgenerator {
    
      public void generatePdfe() throws DocumentException {
          
          
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                PdfPTable table = new PdfPTable(9); // 3 columns
                table.setWidthPercentage(100); // set table width to 100% of page
UserCRUD user = new UserCRUD();
                // Add table headers
          
                    
                      addTableCell(table, "nom ");
                      addTableCell(table, "prenom ");
                      addTableCell(table, "email ");
                      addTableCell(table, "motdepasse ");
                      addTableCell(table, "numerotelephone ");
                      addTableCell(table, "datenaissance ");
                      addTableCell(table, "genre ");
                      addTableCell(table, "role  ");
          
             
              

                // Add table rows
                for (int i = 0; i < user.rechercherTousLesUtilisateurs().size(); i++) {
                  
                    addTableCell(table, ""+user.rechercherTousLesUtilisateurs().get(i).getNom());
                    addTableCell(table, ""+user.rechercherTousLesUtilisateurs().get(i).getPrenom());
                    addTableCell(table, ""+user.rechercherTousLesUtilisateurs().get(i).getEmail());
                    addTableCell(table, ""+user.rechercherTousLesUtilisateurs().get(i).getMotDePasse());
                    addTableCell(table, ""+user.rechercherTousLesUtilisateurs().get(i).getNumeroTelephone());
                    addTableCell(table, ""+user.rechercherTousLesUtilisateurs().get(i).getDateNaissance());
                    addTableCell(table, ""+user.rechercherTousLesUtilisateurs().get(i).getGenre());
                    addTableCell(table, ""+user.rechercherTousLesUtilisateurs().get(i).getRole());
                }

                document.add(table);
                document.close();
                System.out.println("PDF generated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
      private void addTableCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new com.itextpdf.text.Paragraph(text));
        table.addCell(cell);
    
}
}
