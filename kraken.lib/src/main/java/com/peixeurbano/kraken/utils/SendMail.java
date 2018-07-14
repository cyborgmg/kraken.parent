package com.peixeurbano.kraken.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	public static boolean send(final String mail, final String subject, final String msg, final String cc){
		
		Properties props = new Properties();		
		Session session;
		
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
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

              StringBuilder mails = new StringBuilder();
              
              Address[] toUser = InternetAddress.parse(mail+cc);
              message.setRecipients(Message.RecipientType.TO, toUser);  
              
              message.setSubject(subject);//Assunto
              message.setText(msg);
              /**Método para enviar a mensagem criada*/
              Transport.send(message);

              return true;

         } catch (MessagingException e) {
        	 e.printStackTrace();
        	 return false;
        }
	}

}
