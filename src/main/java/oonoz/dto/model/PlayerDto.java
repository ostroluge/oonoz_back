package oonoz.dto.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The Class PlayerDto.
 * 
 * Description :
 * 		A light representation of the player entity to be transferred to the front application.
 */
public class PlayerDto {
	
	/** The id player. */
	private long idPlayer;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The mail. */
	private String mail;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The birth date. */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date birthDate;
	
	/** The is active. */
	private boolean isActive;
	
	private boolean isSupplier;

	/**
	 * Gets the id player.
	 *
	 * @return the id player
	 */
	public long getIdPlayer() {
		return idPlayer;
	}

	/**
	 * Sets the id player.
	 *
	 * @param idPlayer the new id player
	 */
	public void setIdPlayer(long idPlayer) {
		this.idPlayer = idPlayer;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	/**
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isSupplier() {
		return isSupplier;
	}

	public void setSupplier(boolean isSupplier) {
		this.isSupplier = isSupplier;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	

}
