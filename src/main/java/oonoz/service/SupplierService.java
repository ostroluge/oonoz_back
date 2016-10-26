package oonoz.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.Supplier;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.SupplierManagerImpl;
import oonoz.util.CheckUserInformation;
import oonoz.util.MailService;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierManagerImpl supplierManager;
	
	@Autowired
	private MailService mailService;

	@Autowired
	private CheckUserInformation checkUserInformation;
	
	/**
	 * Sign-up a new supplier.
	 * 
	 * @param player
	 *            Contains player's information
	 * @throws WrongInformationException
	 *             If one of the information about the player is wrong.
	 * @throws PlayerAlreadyExistException
	 *             If the player which is signing-up already exist.
	 * @throws MessagingException 
	 */
	public void signUp(Supplier supplier) throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		checkUserInformation.checkUsername(supplier.getUsername());
		checkUserInformation.checkPassword(supplier.getPassword());
		checkUserInformation.checkMail(supplier.getMail());
		checkUserInformation.checkLastName(supplier.getLastName());
		checkUserInformation.checkFirstName(supplier.getFirstName());
		checkUserInformation.checkBirthDate(supplier.getBirthDate());
		
		if(supplier.getIsPrivateIndividual()!=null && !supplier.getIsPrivateIndividual()){
			checkUserInformation.checkSiretNumber(supplier.getSiretNumber());
			checkUserInformation.checkCompanyAddress(supplier.getCompanyAddress());
			checkUserInformation.checkCompanyName(supplier.getCompanyName());
		}
		else{
			supplier.setIsPrivateIndividual(true);
			supplier.setSiretNumber(null);
			supplier.setCompanyAddress(null);
			supplier.setCompanyName(null);
		}
		supplier.setIsValid(false);
		supplier.setIsActive(false);

		String hashPassword=checkUserInformation.hashPassword(supplier.getPassword());
		if(hashPassword!=null){	
			supplier.setPassword(hashPassword);
			supplierManager.create(supplier);
			mailService.sendValidationMail(supplier);
		}
		else{
			throw new WrongInformationException("Password invalid");
		}
		
	}

}
