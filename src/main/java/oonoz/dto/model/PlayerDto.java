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
	protected long idPlayer;
	
	/** The first name. */
	protected String firstName;
	
	/** The last name. */
	protected String lastName;
	
	/** The mail. */
	protected String mail;
	
	/** The username. */
	protected String username;
	
	/** The password. */
	protected String password;
	
	/** The birth date. */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	protected Date birthDate;
	
	/** The is active. */
	protected Boolean isActive;
	
	protected Boolean isSupplier;

	public long getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(long idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsSupplier() {
		return isSupplier;
	}

	public void setIsSupplier(Boolean isSupplier) {
		this.isSupplier = isSupplier;
	}

	
	

}
