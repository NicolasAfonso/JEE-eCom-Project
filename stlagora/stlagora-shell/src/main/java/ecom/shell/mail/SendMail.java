package ecom.shell.mail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void sendMessage(String subject, String text, String destinataire, String copyDest) { 
		Properties properties = new Properties(); 

		properties.setProperty("mail.transport.protocol", "smtp"); 
		properties.setProperty("mail.smtp.host", "smtp.live.com"); 
		properties.setProperty("mail.smtp.user", "stl-agora@outlook.com"); 
		properties.setProperty("mail.from", "stl-agora@outlook.com"); 
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.port", "587");

		Session session = Session.getInstance(properties); 

		MimeMessage message = new MimeMessage(session);

		Transport transport = null;

		try { 

			message.setText(text); 

			message.setSubject(subject); 

			message.addRecipients(Message.RecipientType.TO, destinataire); 

			message.addRecipients(Message.RecipientType.CC, copyDest); 

		} catch (MessagingException e) { 

			e.printStackTrace(); 

		} 


		try { 
			
			session.setDebug(true);
			transport = session.getTransport("smtp"); 
			transport.connect("stl-agora@outlook.com", "azerty123"); 

			transport.sendMessage(message, new Address[] { new InternetAddress(destinataire), 

					new InternetAddress(copyDest) }); 

		} catch (MessagingException e) { 

			e.printStackTrace(); 

		} finally { 

			try { 

				if (transport != null) { 

					transport.close(); 

				} 

			} catch (MessagingException e) { 

				e.printStackTrace(); 

			} 

		} 

	}


	public static void main(String[] args) {
		sendMessage("Envoi de mail marche :)", "Bonjour, Je suis STL-Agora, un super site créé par de supers étudiant(e)s. En plus, je sais envoyer des mails tout seul comme un grand :)", "stl-agora@outlook.com", "afonso.nicolas@live.fr");
	}
}
