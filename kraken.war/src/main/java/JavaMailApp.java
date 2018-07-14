import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailApp
{
      public static void main(final String[] args) {
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             @Override
							protected PasswordAuthentication getPasswordAuthentication()
                             {
                            	 return new PasswordAuthentication("kraken@peixeurbano.com", "dxcfiqmpsxixlabb");
                             }
                        });

            /** Ativa Debug para sessão */
            session.setDebug(true);

            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("kraken@peixeurbano.com")); //Remetente

                  
                  Address[] toUser = InternetAddress.parse("rodrigo.mendes@peixeurbano.com");  
				  
                  message.setRecipients(Message.RecipientType.TO, toUser);                 
                  message.setSubject("kraken");//Assunto
                  message.setText("kraken@peixeurbano.com");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);

                  System.out.println("Feito!!!");

             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
}
