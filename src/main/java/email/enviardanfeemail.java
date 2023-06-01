package email;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

public class enviardanfeemail {

    public boolean enviarEmailDanfe(String destinatario) {

        String remetente = "testing1554@hotmail.com";
        String senha = "Temaki152@";
        String assunto = "Boleto";
        String texto = "Seu boleto esta anexado";

        // Configurações do servidor SMTP
        String host = "smtp-mail.outlook.com";
        int port = 587;

        // Configuração das propriedades
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Criação da sessão
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            // Criação da mensagem de e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);

            // Criação do conteúdo do email
            Multipart multipart = new MimeMultipart();

            // Criação do PDF em memória
            byte[] pdfBytes = createDanfePDF();

            // Criação do anexo do email
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(pdfBytes, "application/pdf")));
            attachmentBodyPart.setFileName("danfe.pdf");

            // Adicionar o anexo ao email
            multipart.addBodyPart(attachmentBodyPart);

            // Adicionar o texto ao corpo do email
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(texto);
            multipart.addBodyPart(textBodyPart);

            // Definir o conteúdo do email
            message.setContent(multipart);

            // Envio do e-mail
            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar o e-mail: " + e.getMessage());
            return false;
        }
    }

    private byte[] createDanfePDF() {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
    
            Barcode128 barcode = new Barcode128();
            barcode.setCode("123456789012");
            barcode.setCodeType(Barcode128.CODE128);
    
            document.add(barcode.createImageWithBarcode(writer.getDirectContent(), null, null));
    
            Paragraph paragraph = new Paragraph("Boleto ficcticio");
            document.add(paragraph);
    
            document.close();
            System.out.println("DANFE gerada com sucesso!");
    
            return outputStream.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }
}    
