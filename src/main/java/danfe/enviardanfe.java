package danfe;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import java.io.FileOutputStream;



public class enviardanfe {


    public void enviar(){

   
    Document document = new Document();

    try {
        // Criação do escritor PDF
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Pc/Downloads/danfe.pdf"));
        document.open();

        // Criação do código de barras
        Barcode128 barcode = new Barcode128();
        barcode.setCode("123456789012");
        barcode.setCodeType(Barcode128.CODE128);
        
        // Desenho do código de barras no documento
        document.add(barcode.createImageWithBarcode(writer.getDirectContent(), null, null));

         // Adicionar um parágrafo de texto
         Paragraph paragraph = new Paragraph("Texto de exemplo na DANFE");
         document.add(paragraph);

        document.close();
        System.out.println("DANFE gerada com sucesso!");
    } catch (DocumentException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
