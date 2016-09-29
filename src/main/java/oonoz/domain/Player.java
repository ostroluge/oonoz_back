package oonoz.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="PLAYER")
@Inheritance(strategy=InheritanceType.JOINED)
public class Player {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idPlayer;
	
	@Column(unique = false, nullable = false,name="FIRSTNAME")
	private String firstName;
	
	@Column(unique = false, nullable = false,name="LASTNAME")
	private String lastName;
	
	@Column(unique = true, nullable = false,name="MAIL")
	private String mail;
	
	@Column(unique=true, nullable = false, name="USERNAME")
	private String username;
	
	@Column(unique=false, nullable = false, name="PASSWORD")
	private String password;
	
	@Column(unique = false, nullable = false,name="BIRTHDATE")
	private Date birthDate;
	
	@Column(unique = false, nullable = false, name="IS_ACTIVE")
	private Boolean isActive;
	
	public void setId(long idPlayer){
		this.idPlayer = idPlayer;
	}
	
	public long getId(){
		return this.idPlayer;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public String getMail(){
		return this.mail;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setBirthDate(Date birthDate){
		this.birthDate = birthDate;
	}
	
	public Date getBirthDate(){
		return this.birthDate;
	}
	
	public void setIsActive(Boolean isActive){
		this.isActive = isActive;
	}
	
	public Boolean isActive(){
		return this.isActive;
	}
}
