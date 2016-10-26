package oonoz.dto.converter;

import org.springframework.stereotype.Component;

import oonoz.domain.Supplier;
import oonoz.dto.model.SupplierDto;

@Component
public class SupplierDtoConverter {

	public Supplier convertToEntity(SupplierDto supplierDto){
		
		Supplier supplier = new Supplier();
		supplier.setLastName(supplierDto.getLastName());
		supplier.setFirstName(supplierDto.getFirstName());
		supplier.setMail(supplierDto.getMail());
		supplier.setUsername(supplierDto.getUsername());
		supplier.setPassword(supplierDto.getPassword());
		supplier.setBirthDate(supplierDto.getBirthDate());
		supplier.setIsActive(supplierDto.isActive());
		supplier.setCompanyAddress(supplierDto.getCompanyAddress());
		supplier.setCompanyName(supplierDto.getCompanyName());
		supplier.setSiretNumber(supplierDto.getSiretNumber());	
		supplier.setIsPrivateIndividual(supplierDto.getIsPrivateIndividual());
		
		return supplier;
	}

}
