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

/**
 * The Class MailService.
 * 
 * Description :
 */
@Component
public class MailService {
	
	/** The smtp host. */
	@Value("${oonoz.mail.host}")
	private String smtpHost;
	
	/** The smtp port. */
	@Value("${oonoz.mail.port}")
	private String smtpPort;

	/** The username. */
	@Value("${oonoz.mail.username}")
	private String username;

	/** The password. */
	@Value("${oonoz.mail.password}")
	private String password;

	/** The sender. */
	@Value("${oonoz.mail.sender}")
	private String sender;
	
	/** The port. */
	@Value("${oonoz.mail.auth}")
	private String authEnable;

	/** The host. */
	@Value("${server.host}")
	private String host;

	/** The port. */
	@Value("${server.port}")
	private int port;
	
	

	/** The props. */
	private Properties props;

	/**
	 * Instantiates a new mail service.
	 */
	public MailService(){
		//Nested comment for SonarQube
	}
	/**
	 * Instantiates a new mail service.
	 *
	 * @param hostMail the host mail
	 * @param portMail the port mail
	 * @param tlsEnable the tls enable
	 * @param authEnable the auth enable
	 */
	public MailService(@Value("${oonoz.mail.host}") String hostMail, @Value("${oonoz.mail.port}") int portMail,@Value("${oonoz.mail.tls_enable}") boolean tlsEnable, @Value("${oonoz.mail.auth}") String authEnable) {

		
	}

	/**
	 * I send an email to a user.
	 *
	 * @param player the player
	 * @throws MessagingException             raised if there is a problem.
	 */
	public void sendValidationMail(Player player) throws MessagingException {
		
		if(props==null){
			
			this.props = new Properties();
			this.props.put("mail.smtp.host", smtpHost);
			this.props.put("mail.smtp.port", smtpPort);
			this.props.put("mail.smtp.starttls.enable", true);
			this.props.setProperty("mail.smtp.auth",authEnable);
		}

		String title = "Oonoz - Validation du compte";

		Session session = this.getSession();

		String content = subscriptionMailContentFor(player);
		Transport.send(this.generateMessageFrom(player.getMail(), session, title, content));
	}
	
	
	/**
	 * Send new generate password mail.
	 *
	 * @param player the player
	 * @throws MessagingException the messaging exception
	 */
	public void sendNewGeneratePasswordMail(Player player) throws MessagingException{
		
		if(props==null){
			
			this.props = new Properties();
			this.props.put("mail.smtp.host", smtpHost);
			this.props.put("mail.smtp.port", smtpPort);
			this.props.put("mail.smtp.starttls.enable", true);
			this.props.setProperty("mail.smtp.auth",authEnable);
		}
		String title = "Oonoz - Mot de passe oublié";
		Session session =  this.getSession();

		String content = generateNewPasswordMailContentFor(player);
		Transport.send(this.generateMessageFrom(player.getMail(), session, title, content));

	}

	/**
	 * I generate a MimeMessage to be send in a mail.
	 *
	 * @param mailDestination the mail destination
	 * @param session            The session.
	 * @param title            The title of the mail.
	 * @param content            The content of the mail.
	 * @return The mime message created.
	 * @throws MessagingException             raised if there is a problem.
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
	 * Create a session with the SMTP server.
	 *
	 * @return the session
	 */
	private Session getSession(){
		
		return Session.getInstance(this.props, new Authenticator() {
			protected PasswordAuthentication pa = new PasswordAuthentication(username, password);

			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return pa;
			}
		});
		
	}

	/**
	 * I generate a mail for a subscription. This mail will contain a link to
	 * activate his account.
	 *
	 * @param player the player
	 * @return The content of the mail.
	 */
	private String subscriptionMailContentFor(Player player) {
		return "Bonjour,\n" + "\n" + "Vous recevez cet email car vous vous êtes inscrit à Oonoz.\n"
				+ "S'il s'agit de vous, cliquez juste sur le lien ci dessous ou copiez/collez le dans votre navigateur.\n"
				+ "\n" + "http://" + this.host + ":63342"+"/oonoz_front/app/index.html#mailValidation/"+player.getMail()+"/"+player.getMail().hashCode()+"\n" + "\n"
				+ "Si vous n'avez pas demandé cette inscription, veuillez ignorer cet email..\n" + "Merci,\n"
				+ "L'équipe Oonoz.";
	}
	
	/**
	 * I generate a mail for a new password. 
	 *
	 * @param player the player
	 * @return The content of the mail.
	 */
	private String generateNewPasswordMailContentFor(Player player) {
		return "Bonjour,\n" + "\n" + "Vous avez fais une demande de mot de passe oublié.\n"
				+ "Nouveau mot de passe: "+ player.getPassword()+"\n"
				+ "Si vous n'avez pas demandé un nouveau mot de passe, veuillez le signaler rapidement.\n" + "Merci,\n"
				+ "L'équipe Oonoz.";
	}

}
