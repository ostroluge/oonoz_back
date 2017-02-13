package oonoz.dto.model;

/**
 * The Class SupplierDto.
 * 
 * Description :
 */
public class SupplierDto extends PlayerDto {
	
	/**
	 * Instantiates a new supplier dto.
	 */
	public SupplierDto(){
		//Nested comment for SonarQube
	}
	
	/** The is valid. */
	private Boolean isValid;
	
	/** The is private individual. */
	private Boolean isPrivateIndividual;
	
	/** The company name. */
	private String companyName;
	
	/** The company address. */
	private String companyAddress;
	
	/** The siret number. */
	private String siretNumber;

	/**
	 * Instantiates a new supplier dto.
	 *
	 * @param playerDto the player dto
	 */
	public SupplierDto(PlayerDto playerDto){
		this.idPlayer=playerDto.idPlayer;
		this.firstName=playerDto.firstName;
		this.lastName=playerDto.lastName;
		this.username=playerDto.username;
		this.password=playerDto.password;
		this.isActive=playerDto.isActive;
		this.isSupplier=playerDto.isSupplier;
		this.mail=playerDto.mail;
		this.birthDate=playerDto.birthDate;
	}
	
	/**
	 * Gets the is valid.
	 *
	 * @return the is valid
	 */
	public Boolean getIsValid() {
		return isValid;
	}

	/**
	 * Sets the is valid.
	 *
	 * @param isValid the new is valid
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * Gets the is private individual.
	 *
	 * @return the is private individual
	 */
	public Boolean getIsPrivateIndividual() {
		return isPrivateIndividual;
	}

	/**
	 * Sets the is private individual.
	 *
	 * @param isPrivateIndividual the new is private individual
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
