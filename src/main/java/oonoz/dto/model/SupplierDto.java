package oonoz.dto.model;



public class SupplierDto extends PlayerDto {
	
	public SupplierDto(){
		
	}
	
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
	
	
	private Boolean isValid;
	
	private Boolean isPrivateIndividual;
	
	private String companyName;
	
	private String companyAddress;
	
	private String siretNumber;

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Boolean getIsPrivateIndividual() {
		return isPrivateIndividual;
	}

	public void setIsPrivateIndividual(Boolean isPrivateIndividual) {
		this.isPrivateIndividual = isPrivateIndividual;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getSiretNumber() {
		return siretNumber;
	}

	public void setSiretNumber(String siretNumber) {
		this.siretNumber = siretNumber;
	}
	
	

}
