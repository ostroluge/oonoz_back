package oonoz.dto.converter;

import org.springframework.stereotype.Component;

import oonoz.domain.Player;
import oonoz.domain.Supplier;
import oonoz.dto.model.PlayerDto;
import oonoz.dto.model.SupplierDto;

@Component
public class SupplierDtoConverter extends PlayerDtoConverter {

	public Supplier convertToEntity(SupplierDto supplierDto){
		
		
		Player player= super.convertToEntity(supplierDto);
		Supplier supplier=new Supplier(player);
		supplier.setCompanyAddress(supplierDto.getCompanyAddress());
		supplier.setCompanyName(supplierDto.getCompanyName());
		supplier.setSiretNumber(supplierDto.getSiretNumber());	
		supplier.setIsPrivateIndividual(supplierDto.getIsPrivateIndividual());
		supplier.setIsValid(supplierDto.getIsValid());
		
		return supplier;
	}
	
	
	
	public SupplierDto convertToDto(Supplier supplier){
		
		PlayerDto playerDto=super.convertToDto(supplier);
		SupplierDto supplierDto= new SupplierDto(playerDto);
		
		supplierDto.setCompanyAddress(supplier.getCompanyAddress());
		supplierDto.setCompanyName(supplier.getCompanyName());
		supplierDto.setSiretNumber(supplier.getSiretNumber());
		supplierDto.setIsPrivateIndividual(supplier.getIsPrivateIndividual());
		supplierDto.setIsValid(supplier.getIsValid());
		
		return supplierDto;
	}

}
