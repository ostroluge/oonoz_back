package oonoz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="SUPPLIER")
@PrimaryKeyJoinColumn(name="ID_PLAYER")
public class Supplier extends Player{

	@Column(unique = false, nullable = false,name="IS_VALID")
	private Boolean isValid;

	@Column(unique = false, nullable = false, name="IS_PRIVATE_INDIVIDUAL")
	private Boolean isPrivateIndividual;
	
	@Column(unique = false, nullable = true,name="COMPANY_NAME")
	private String companyName;

	@Column(unique = false, nullable = true,name="COMPANY_ADDRESS")
	private String companyAddress; 

	@Column(unique = true, nullable = true,name="SIRET_NUMBER")
	@Length(max = 14, message = "The field must be less than 14 characters")
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
