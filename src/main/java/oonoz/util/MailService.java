package oonoz.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import oonoz.domain.Player;

@Component
public class MailService {

	@Value("${oonoz.mail.username}")
	private String username;

	@Value("${oonoz.mail.password}")
	private String password;

	/*@Value("${oonoz.mail.ssl.enable}")
	private boolean sslEnable;*/

	@Value("${oonoz.mail.sender}")
	private String sender;

	@Value("${oonoz.host}")
	private String host;

	@Value("${oonoz.port}")
	private int port;

	private Properties props;

	public MailService(@Value("${oonoz.mail.host}") String hostMail, @Value("${oonoz.mail.port}") int portMail,@Value("${oonoz.mail.tls.enable}") boolean tlsEnable, @Value("${oonoz.mail.auth}") String authEnable) {

		this.props = new Properties();
		this.props.put("mail.smtp.host", hostMail);
		this.props.put("mail.smtp.port", portMail);
		this.props.put("mail.smtp.starttls.enable", tlsEnable);
		this.props.setProperty("mail.smtp.auth",authEnable);
	}

	/**
	 * I send an email to a user.
	 *
	 * @param aUser
	 *            The user that need to receive the mail.
	 * @param title
	 *            the title of the mail.
	 * @param content
	 *            The content of the mail.
	 * @throws MessagingException
	 *             raised if there is a problem.
	 */
	public void sendValidationMail(Player player) throws MessagingException {

		String title = "Validation du compte Oonoz";

		Session session = Session.getInstance(this.props, new Authenticator() {
			protected PasswordAuthentication pa = new PasswordAuthentication(username, password);

			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return pa;
			}
		});

		String content = subscriptionMailContentFor(player);
		Transport.send(generateMessageFrom(player.getMail(), session, title, content));
	}

	/**
	 * I generate a MimeMessage to be send in a mail.
	 *
	 * @param aUser
	 *            The user that need the mail.
	 * @param session
	 *            The session.
	 * @param title
	 *            The title of the mail.
	 * @param content
	 *            The content of the mail.
	 * @return The mime message created.
	 * @throws MessagingException
	 *             raised if there is a problem.
	 */
	private MimeMessage generateMessageFrom(String mailDestination, Session session, String title, String content)
			throws MessagingException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(this.sender));
		InternetAddress[] address = { new InternetAddress(mailDestination) };
		message.setRecipients(Message.RecipientType.TO, address);
		message.setSubject(title);
		message.setSentDate(new Date());
		message.setText(content);
		return message;
	}

	/**
	 * I generate a mail for a subscription. This mail will contain a link to
	 * activate his account.
	 *
	 * @param Player
	 *            The player that subscribe.
	 * @return The content of the mail.
	 */
	private String subscriptionMailContentFor(Player player) {
		return "Bonjour,\n" + "\n" + "Vous recevez cet email car vous vous êtes inscrit à Oonoz.\n"
				+ "S'il s'agit de vous, cliquez juste sur le lien ci dessous ou copiez/collez le dans votre navigateur.\n"
				+ "\n" + "http://" + this.host + ":" + this.port + "/user/validationMail?mail="
				+ player.getMail().toString() + "&cle=" + player.getMail().hashCode() + "\n" + "\n"
				+ "Si vous n'avez pas demandé cette inscription, veuillez ignorer cet email..\n" + "Merci,\n"
				+ "L'équipe Oonoz.";
	}

}
