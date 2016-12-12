package oonoz.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.Supplier;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.SupplierManagerImpl;
import oonoz.util.CheckUserInformation;
import oonoz.util.MailService;

/**
 * The Class SupplierService.
 * Description :
 */
@Service
public class SupplierService {

	/** The supplier manager. */
	@Autowired
	private SupplierManagerImpl supplierManager;


	/** The mail service. */
	@Autowired
	private MailService mailService;

	/** The check user information. */
	@Autowired
	private CheckUserInformation checkUserInformation;

	/**
	 * Sign-up a new supplier.
	 *
	 * @param supplier the supplier
	 * @throws WrongInformationException             If one of the information about the player is wrong.
	 * @throws PlayerAlreadyExistException             If the player which is signing-up already exist.
	 * @throws MessagingException the messaging exception
	 */
	public void signUp(Supplier supplier)
			throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		this.checkInformationSignUpSupplier(supplier);

		supplier.setIsValid(false);
		supplier.setIsActive(false);
		supplier.setIsSupplier(true);
		String hashPassword = checkUserInformation.hashPassword(supplier.getPassword());
		if (hashPassword != null) {
			supplier.setPassword(hashPassword);
			supplierManager.create(supplier);
			mailService.sendValidationMail(supplier);
		} else {
			throw new WrongInformationException("Password invalid");
		}

	}

	/**
	 * Sign-up a new supplier already active without mail.
	 *
	 * @param supplier the supplier
	 * @throws WrongInformationException             If one of the information about the player is wrong.
	 * @throws PlayerAlreadyExistException             If the player which is signing-up already exist.
	 * @throws MessagingException the messaging exception
	 */
	public void signUpByAdmin(Supplier supplier) throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		this.checkInformationSignUpSupplier(supplier);
		
		supplier.setIsValid(true);
		supplier.setIsActive(true);
		supplier.setIsSupplier(true);
		String hashPassword=checkUserInformation.hashPassword(supplier.getPassword());
		if(hashPassword!=null){	
			supplier.setPassword(hashPassword);
			supplierManager.create(supplier);
		}
		else{
			throw new WrongInformationException("Password invalid");
		}	
	}
	
	/**
	 * Update supplier.
	 *
	 * @param supplier the supplier
	 * @throws WrongInformationException             the wrong information exception
	 * @throws PlayerNotExistException the player not exist exception
	 */
	public void updateSupplier(Supplier supplier) throws WrongInformationException, PlayerNotExistException {

		checkUserInformation.checkUsername(supplier.getUsername());
		// checkUserInformation.checkPassword(supplier.getPassword());
		checkUserInformation.checkMail(supplier.getMail());
		checkUserInformation.checkLastName(supplier.getLastName());
		checkUserInformation.checkFirstName(supplier.getFirstName());
		checkUserInformation.checkBirthDate(supplier.getBirthDate());

		Supplier newSupplier= (Supplier)supplierManager.findById(supplier.getIdPlayer());
		
		if (newSupplier != null) {
			newSupplier.setUsername(supplier.getUsername());
			newSupplier.setLastName(supplier.getLastName());
			newSupplier.setFirstName(supplier.getFirstName());
			newSupplier.setMail(supplier.getMail());
			newSupplier.setBirthDate(supplier.getBirthDate());
			newSupplier.setIsActive(supplier.getIsActive());
			newSupplier.setIsPrivateIndividual(supplier.getIsPrivateIndividual());
			newSupplier.setIsValid(supplier.getIsValid());
			newSupplier.setIsSupplier(supplier.getIsSupplier());

			if (newSupplier.getIsPrivateIndividual() != null && !newSupplier.getIsPrivateIndividual()) {
				checkUserInformation.checkSiretNumber(supplier.getSiretNumber());
				checkUserInformation.checkCompanyAddress(supplier.getCompanyAddress());
				checkUserInformation.checkCompanyName(supplier.getCompanyName());
				newSupplier.setCompanyAddress(supplier.getCompanyAddress());
				newSupplier.setCompanyName(supplier.getCompanyName());
				newSupplier.setSiretNumber(supplier.getSiretNumber());

			}

			supplierManager.update(newSupplier);
		} else {
			throw new PlayerNotExistException("The player does not exist !");
		}
	}
	
	/**
	 * Gets the supplier request.
	 *
	 * @return the supplier request
	 */
	public List<Supplier> getSupplierRequest(){
		List<Supplier> supplierList = supplierManager.findNotValidSupplier();
		return supplierList;
	}
	
	/**
	 * Refuse supplier request.
	 *
	 * @param idPlayer the id player
	 */
	public void refuseSupplierRequest(long idPlayer){
		supplierManager.refuseSupplierRequest(idPlayer);
	}

	/**
	 * Accept supplier request.
	 *
	 * @param idPlayer the id player
	 */
	public void acceptSupplierRequest(long idPlayer) {
		supplierManager.acceptSupplierRequest(idPlayer);
	}

	/**
	 * Check information sign up supplier.
	 *
	 * @param supplier the supplier
	 * @throws WrongInformationException the wrong information exception
	 */
	private void checkInformationSignUpSupplier(Supplier supplier) throws WrongInformationException{
		checkUserInformation.checkUsername(supplier.getUsername());
		checkUserInformation.checkPassword(supplier.getPassword());
		checkUserInformation.checkMail(supplier.getMail());
		checkUserInformation.checkLastName(supplier.getLastName());
		checkUserInformation.checkFirstName(supplier.getFirstName());
		checkUserInformation.checkBirthDate(supplier.getBirthDate());

		if (supplier.getIsPrivateIndividual() != null && !supplier.getIsPrivateIndividual()) {
			checkUserInformation.checkSiretNumber(supplier.getSiretNumber());
			checkUserInformation.checkCompanyAddress(supplier.getCompanyAddress());
			checkUserInformation.checkCompanyName(supplier.getCompanyName());
		} else {
			supplier.setIsPrivateIndividual(true);
			supplier.setSiretNumber(null);
			supplier.setCompanyAddress(null);
			supplier.setCompanyName(null);
		}
	}
	
	/**
	 * Gets the suppliers.
	 *
	 * @return the suppliers
	 */
	public List<Supplier> getSuppliers() {
		return supplierManager.getSuppliers();
	}
}
