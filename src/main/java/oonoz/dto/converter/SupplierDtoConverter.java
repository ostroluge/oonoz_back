package oonoz.dto.converter;

import org.springframework.stereotype.Component;

import oonoz.domain.Player;
import oonoz.domain.Supplier;
import oonoz.dto.model.PlayerDto;
import oonoz.dto.model.SupplierDto;

/**
 * The Class SupplierDtoConverter.
 * 
 * Description :
 */
@Component
public class SupplierDtoConverter extends PlayerDtoConverter {

	/**
	 * Convert to entity.
	 *
	 * @param supplierDto the supplier dto
	 * @return the supplier
	 */
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
	
	
	
	/**
	 * Convert to dto.
	 *
	 * @param supplier the supplier
	 * @return the supplier dto
	 */
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
