package aplicacion.modelo.ejb;

import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.pojo.Mail;

@Stateless
@LocalBean
public class MailEJB {

	private static final Logger LOG = LogSingleton.getInstance().getLoggerMailEJB();

	public boolean sendMail(String para, String asunto, String mensaje, Mail correo) {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", correo.getHost());
		prop.put("mail.smtp.port", correo.getPort());
		prop.put("mail.smtp.ssl.trust", correo.getHost());

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(correo.getUsername(), correo.getPassword());
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correo.getUsername()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(asunto);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(mensaje, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			return true;
		} catch (Exception e) {
			LOG.error("ERROR Correo MailEJB", e);
			return false;
		}
	}

	public Mail getMail(String string, int i, String string2, String string3) {
		return new Mail("smtp.gmail.com", 587, "imcpractica@gmail.com", "contrasenyaimc");
	}
}
