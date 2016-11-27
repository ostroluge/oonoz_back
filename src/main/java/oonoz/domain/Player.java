package oonoz.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * The Class Player.
 * 
 * Description :
 * 		
 */
@Entity
@Table(name="PLAYER")
@Inheritance(strategy=InheritanceType.JOINED)
@DynamicUpdate(value=true)
public class Player {

	
	/** The id player. */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long idPlayer;
	
	
	/** The first name. */
	@Column(unique = false, nullable = false,name="FIRSTNAME")
	protected String firstName;
	
	/** The last name. */
	@Column(unique = false, nullable = false,name="LASTNAME")
	protected String lastName;
	
	/** The mail. */
	@Column(unique = true, nullable = false,name="MAIL")
	protected String mail;
	
	/** The username. */
	@Column(unique=true, nullable = false, name="USERNAME")
	protected String username;
	
	/** The password. */
	@Column(unique=false, nullable = false, name="PASSWORD")
	protected String password;
	

	/** The birth date. */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone="CET")
	@Column(unique = false, nullable = false,name="BIRTHDATE")
	protected Date birthDate;
	
	/** The is active. */
	@Column(unique = false, nullable = false, name="IS_ACTIVE")
	protected Boolean isActive;
	
	@Column(unique = false, nullable = false, name="IS_SUPPLIER")
	protected Boolean isSupplier;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	protected Authorities authorities;
	
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getIdPlayer() {
		return idPlayer;
	}

	/**
	 * Sets the id.
	 *
	 * @param idPlayer the new id
	 */
	public void setIdPlayer(long idPlayer) {
		this.idPlayer = idPlayer;
	}

	

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName(){
		return this.firstName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName(){
		return this.lastName;
	}
	
	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail){
		this.mail = mail;
	}
	
	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail(){
		return this.mail;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername(){
		return this.username;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword(){
		return this.password;
	}
	
	/**
	 * Sets the birth date.
	 *
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(Date birthDate){
		this.birthDate = birthDate;
	}
	
	/**
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public Date getBirthDate(){
		return this.birthDate;
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

	public Authorities getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Authorities authorities) {
		this.authorities = authorities;
	}
	
	
}
