import java.io.File;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

	public static void send(final Set<String> to, final String from, final String password, Set<String> cc, String subject, String text, String html, Set<String> paths) {

		
		//Verificar se os campos obrigatórios foram preenchidos
		validation(to, from, password);
		
		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, password);

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			
			// Seta a lista de endereços de destino		
			for (String address : to) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
			}
			
			// Seta a lista de endereços para cópia
			if (!cc.isEmpty() && cc != null) {
				
				for (String address : cc) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(address));
				}
				
			}

			if (!subject.isBlank() && subject != null) {
				// Set Subject: header field
				message.setSubject(subject);
			}
			
			if (!text.isBlank() && text != null) {
				// Now set the actual message
				message.setText(text);
			}
			
			// Verifica codições para anexos
			if (!html.isBlank() && html != null && !paths.isEmpty() && paths != null) {
				attachments(message, html, paths);
			}
			
			else if (!html.isBlank() && html != null && (paths.isEmpty() || paths == null)) {
				attachments(message, html);
			}
			
			else if ((html.isBlank() || html == null) && paths.isEmpty() && paths != null) {
				attachments(message, paths);
			}
			
			System.out.println("sending...");
			
			// Send message
			Transport.send(message);
			
			System.out.println("Sent message successfully....");
			
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	//-----------------------------------------------------------------------------------------------------------------
	
	
	

	private static void attachments(MimeMessage message, String html, Set<String> paths) throws MessagingException {

		// Adiciona um html como anexo
		String htmlMessage = "<html>" + html + "</html>";
		Multipart multipart = new MimeMultipart();
		MimeBodyPart attachmentHtml = new MimeBodyPart();
		attachmentHtml.setContent(htmlMessage, "text/html; charset=UTF-8");
		multipart.addBodyPart(attachmentHtml);

		for (String path : paths) {

			// Adiciona um arquivo como anexo
			File file = new File(path);
			MimeBodyPart attachment = new MimeBodyPart();
			attachment.setDataHandler(new DataHandler(new FileDataSource(file)));
			attachment.setFileName(file.getName());
			multipart.addBodyPart(attachment);

		}
		
		message.setContent(multipart);

	}
	
	private static void attachments(MimeMessage message, String html) throws MessagingException {

		// Adiciona um html como anexo
		String htmlMessage = "<html>" + html + "</html>";
		Multipart multipart = new MimeMultipart();
		MimeBodyPart attachmentHtml = new MimeBodyPart();
		attachmentHtml.setContent(htmlMessage, "text/html; charset=UTF-8");
		multipart.addBodyPart(attachmentHtml);
	
		message.setContent(multipart);

	}
	
	private static void attachments(MimeMessage message, Set<String> paths) throws MessagingException {
		
		Multipart multipart = new MimeMultipart();

		for (String path : paths) {

			// Adiciona um arquivo como anexo
			File file = new File(path);
			MimeBodyPart attachment = new MimeBodyPart();
			attachment.setDataHandler(new DataHandler(new FileDataSource(file)));
			attachment.setFileName(file.getName());
			multipart.addBodyPart(attachment);

		}
		
		message.setContent(multipart);

	}
	
	private static void validation(Set<String> to, String from, String password) {
		
		if (to.isEmpty() || to == null) {
			throw new RuntimeException("To is required");
		}
		
		if (from.equals("") || from == null) {
			throw new RuntimeException("From is required");
		}
		
		if (password.equals("") || password == null) {
			throw new RuntimeException("Password is required");
		}
		
		
	}

}
