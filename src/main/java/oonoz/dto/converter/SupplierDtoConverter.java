package oonoz.dto.converter;

import org.springframework.stereotype.Component;

import oonoz.domain.Supplier;
import oonoz.dto.model.SupplierDto;

@Component
public class SupplierDtoConverter extends PlayerDtoConverter {

	public Supplier convertToEntity(SupplierDto supplierDto){
		
		
		Supplier supplier=(Supplier) super.convertToEntity(supplierDto);
		
		/*supplier.setLastName(supplierDto.getLastName());
		supplier.setFirstName(supplierDto.getFirstName());
		supplier.setMail(supplierDto.getMail());
		supplier.setUsername(supplierDto.getUsername());
		supplier.setPassword(supplierDto.getPassword());
		supplier.setBirthDate(supplierDto.getBirthDate());
		supplier.setIsActive(supplierDto.isActive());*/
		supplier.setCompanyAddress(supplierDto.getCompanyAddress());
		supplier.setCompanyName(supplierDto.getCompanyName());
		supplier.setSiretNumber(supplierDto.getSiretNumber());	
		supplier.setIsPrivateIndividual(supplierDto.getIsPrivateIndividual());
		
		return supplier;
	}
	
	
	public SupplierDto convertToDto(Supplier supplier){
		
		SupplierDto supplierDto=(SupplierDto)super.convertToDto(supplier);
		
		supplierDto.setCompanyAddress(supplier.getCompanyAddress());
		supplierDto.setCompanyName(supplier.getCompanyName());
		supplierDto.setSiretNumber(supplier.getSiretNumber());
		supplierDto.setIsPrivateIndividual(supplier.getIsPrivateIndividual());
		
		return supplierDto;
	}

}
