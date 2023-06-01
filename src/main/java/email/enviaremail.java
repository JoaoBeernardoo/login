package email;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.*;

import com.google.protobuf.TextFormat;

import java.util.Properties;



public class enviaremail {
    


    public  void enviar(String Email,String Assunto, String Texto){

        String userEmail = "testing1554@hotmail.com";
        String password = "Temaki152@";
        String recipientEmail = Email;

        // Configurações do servidor SMTP do Outlook
        String host = "smtp-mail.outlook.com";
        String port = "587";

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
                return new PasswordAuthentication(userEmail, password);
            }
        });

        try {
            // Criação da mensagem de e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(Assunto);
            message.setText(Texto);

            // Envio do e-mail
            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }

}
