package oonoz.back.domain;

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

	@Column(unique = false, nullable = false,name="COMPANY_NAME")
	private String companyName;

	@Column(unique = false, nullable = false,name="COMPANY_ADDRESS")
	private String companyAddress; 

	@Column(unique = true, nullable = false,name="SIRET_NUMBER")
	@Length(max = 14, message = "The field must be less than 14 characters")
	private String siretNumber;
}
