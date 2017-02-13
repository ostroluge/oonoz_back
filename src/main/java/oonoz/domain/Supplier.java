package oonoz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;


/**
 * The Class Supplier.
 * 
 * Description :
 */
@Entity
@Table(name="SUPPLIER")
@PrimaryKeyJoinColumn(name="ID_PLAYER")
@Inheritance(strategy=InheritanceType.JOINED)
public class Supplier extends Player{
	
	
	public Supplier(){
		
	}
	
	public Supplier(Player player){
		this.idPlayer=player.idPlayer;
		this.firstName=player.firstName;
		this.lastName=player.lastName;
		this.birthDate=player.birthDate;
		this.mail=player.mail;
		this.username=player.username;
		this.password=player.password;
		this.isActive=player.isActive;
		this.isSupplier=player.isSupplier;
		this.authorities=player.authorities;
	}
	
	/** True if the supplier has validated his sign-up. */
	@Column(unique = false, nullable = false,name="IS_VALID")
	private Boolean isValid;

	/** True if the supplier represents a company. */
	@Column(unique = false, nullable = false, name="IS_PRIVATE_INDIVIDUAL")
	private Boolean isPrivateIndividual;
	
	
	/** The company name. */
	@Column(unique = false, nullable = true,name="COMPANY_NAME")
	private String companyName;

	
	/** The company address. */
	@Column(unique = false, nullable = true,name="COMPANY_ADDRESS")
	private String companyAddress; 


	/** The siret number. */
	@Column(unique = true, nullable = true,name="SIRET_NUMBER")
	@Length(max = 14, message = "The field must be less than 14 characters")
	private String siretNumber;

	/**
	 * Gets the checks if is valid.
	 *
	 * @return the checks if is valid
	 */
	public Boolean getIsValid() {
		return isValid;
	}

	/**
	 * Sets the checks if is valid.
	 *
	 * @param isValid the new checks if is valid
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * Gets the checks if is private individual.
	 *
	 * @return the checks if is private individual
	 */
	public Boolean getIsPrivateIndividual() {
		return isPrivateIndividual;
	}

	/**
	 * Sets the checks if is private individual.
	 *
	 * @param isPrivateIndividual the new checks if is private individual
	 */
	public void setIsPrivateIndividual(Boolean isPrivateIndividual) {
		this.isPrivateIndividual = isPrivateIndividual;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the company address.
	 *
	 * @return the company address
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * Sets the company address.
	 *
	 * @param companyAddress the new company address
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * Gets the siret number.
	 *
	 * @return the siret number
	 */
	public String getSiretNumber() {
		return siretNumber;
	}

	/**
	 * Sets the siret number.
	 *
	 * @param siretNumber the new siret number
	 */
	public void setSiretNumber(String siretNumber) {
		this.siretNumber = siretNumber;
	}

}
