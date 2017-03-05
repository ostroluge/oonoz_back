package oonoz.controller;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Player;
import oonoz.domain.QCM;
import oonoz.domain.Supplier;
import oonoz.dto.converter.PlayerDtoConverter;
import oonoz.dto.converter.SupplierDtoConverter;
import oonoz.dto.model.PlayerDto;
import oonoz.dto.model.SupplierDto;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QCMValidationException;
import oonoz.exception.WrongInformationException;
import oonoz.service.AdminService;
import oonoz.service.PlayerService;
import oonoz.service.QCMService;
import oonoz.service.SupplierService;
import oonoz.util.FilteredSearch;
import oonoz.util.StringResponse;

/**
 * The Class AdminController.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	/** The admin service. */
	@Autowired
	private AdminService adminService;

	/** The qcm service. */
	@Autowired
	private QCMService qcmService;
	
	/** The player service. */
	@Autowired
	private PlayerService playerService;

	/** The supplier service. */
	@Autowired
	private SupplierService supplierService;

	/** The player dto converter. */
	@Autowired
	private PlayerDtoConverter playerDtoConverter;

	/** The supplier dto converter. */
	@Autowired
	private SupplierDtoConverter supplierDtoConverter;

	/**
	 * Filtered search.
	 *
	 * @param filteredSearch the filtered search
	 * @return the response entity
	 */
	@RequestMapping(value = "/filteredSearch", method = RequestMethod.POST)
	public ResponseEntity<Page<PlayerDto>> filteredSearch(@RequestBody FilteredSearch filteredSearch){

		try{
			return new ResponseEntity<>(playerService.filteredSearch(filteredSearch), HttpStatus.OK);
		}
		catch(WrongInformationException e){
			logger.error("The page number must be greater or equal than zero !",e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Update user.
	 *
	 * @param playerDto the player dto
	 * @return the response entity
	 */
	@RequestMapping(value = "/updatePlayer", method = RequestMethod.PUT)
	public ResponseEntity<String> updateUser(@RequestBody PlayerDto playerDto){

		Player player=playerDtoConverter.convertToEntity(playerDto);
		try{
			playerService.updatePlayer(player);
		}
		catch(PlayerNotExistException e){
			logger.error("The player does not exist !",e);
			return new ResponseEntity<>("The supplier does not exist !", HttpStatus.BAD_REQUEST);
		}
		catch(WrongInformationException e){
			logger.error("Information about player are not valid !",e);
			return new ResponseEntity<>("Information about supplier are not valid !", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("", HttpStatus.OK);
	}
    
    /**
     * Update supplier.
     *
     * @param supplierDto the supplier dto
	 * @return the response entity
	 */
	@RequestMapping(value = "/updateSupplier", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSupplier(@RequestBody SupplierDto supplierDto){

		Supplier supplier=supplierDtoConverter.convertToEntity(supplierDto);
		supplier.setIdPlayer(supplierDto.getIdPlayer());
		try{
			supplierService.updateSupplier(supplier);
		}
		catch(PlayerNotExistException e){
			logger.error("The supplier does not exist !",e);
			return new ResponseEntity<>("The supplier does not exist !", HttpStatus.BAD_REQUEST);
		}
		catch(WrongInformationException e){
			logger.error("Information about supplier are not valid !",e);
			return new ResponseEntity<>("Information about supplier are not valid !", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("", HttpStatus.OK);
	}
    
    /**
     * Delete user.
     *
     * @param idPlayer the id player
     * @return the response entity
     * @throws PlayerNotExistException the player not exist exception
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestParam(value = "idPlayer", defaultValue = "") Long idPlayer) throws PlayerNotExistException{
    	
    	try{
    		playerService.deletePlayer(idPlayer);
    	}
    	catch(PlayerNotExistException e){
    		logger.error("The user does not exist !",e);
    		return new ResponseEntity<>("The user does not exist !", HttpStatus.BAD_REQUEST);
    	}
    	
    	return new ResponseEntity<>("", HttpStatus.OK);
    }

    
    /**
     * Change status user.
     *
     * @param idPlayer the id player
     * @return the response entity
     * @throws PlayerNotExistException the player not exist exception
     */
    @RequestMapping(value = "/changeStatusUser", method = RequestMethod.GET)
    public ResponseEntity<String> changeStatusUser(@RequestParam(value = "idPlayer", defaultValue = "") Long idPlayer) throws PlayerNotExistException{
    	
    	try{
    		if(idPlayer!=null)
    			playerService.changeStatusUser(idPlayer);
    	}
    	catch(PlayerNotExistException e){
    		logger.error("The user does not exist !",e);
    		return new ResponseEntity<>("The user does not exist !", HttpStatus.BAD_REQUEST);
    	}
    	
    	return new ResponseEntity<>("", HttpStatus.OK);
    }

	/**
	 * Authenticate users.
	 *
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@RequestMapping(value = "/getSupplierRequest", method = RequestMethod.GET)
	public ResponseEntity<List<Supplier>> getSupplierRequest(HttpServletRequest request) {

		supplierService.getSupplierRequest();
		return new ResponseEntity<>(supplierService.getSupplierRequest(), HttpStatus.OK);
	}

	/**
	 * Refuse supplier request.
	 *
	 * @param idPlayer the id player
	 * @return the response entity
	 */
	@RequestMapping(value = "/refuseSupplierRequest", method = RequestMethod.DELETE)
	public ResponseEntity<StringResponse> refuseSupplierRequest(
			@RequestParam(value = "idPlayer", defaultValue = "") Long idPlayer) {
		supplierService.refuseSupplierRequest(idPlayer);
		StringResponse response = new StringResponse();
		response.setResponse("La demande a été refusée");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Accept supplier request.
	 *
	 * @param idPlayer the id player
	 * @return the response entity
	 */
	@RequestMapping(value = "/acceptSupplierRequest", method = RequestMethod.POST)
	public ResponseEntity<StringResponse> acceptSupplierRequest(
			@RequestParam(value = "idPlayer", defaultValue = "") Long idPlayer) {
		supplierService.acceptSupplierRequest(idPlayer);
		StringResponse response = new StringResponse();
		response.setResponse("La demande a été acceptée");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Creates the player account.
	 *
	 * @param playerDto the player dto
	 * @return the response entity
	 */
	@RequestMapping(value = "/createPlayerAccount", method = RequestMethod.POST)
	public ResponseEntity<StringResponse> createPlayerAccount(@RequestBody PlayerDto playerDto) {

		Player player = playerDtoConverter.convertToEntity(playerDto);
		StringResponse response = new StringResponse();
		try {
			this.playerService.signUpByAdmin(player);
		} catch (WrongInformationException e) {
			logger.error("Mauvaise information", e);
			response.setResponse("The information of sign-up are not valid !");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} catch (PlayerAlreadyExistException e) {
			logger.error("Ce joueur existe déjà", e);
			response.setResponse("The player already exists !");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		} catch (MessagingException e) {
			logger.error("Impossible d'envoyer le message", e);
			response.setResponse("A error occurs when sending validation mail !");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		response.setResponse("Player created !");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}


	/**
	 * create supplier account.
	 *
	 * @param supplierDto the supplier dto
	 * 		The light representation of the supplier entity
	 * @return the response entity
	 */
	@RequestMapping(value = "/createSupplierAccount", method = RequestMethod.POST)
	public ResponseEntity<String> createSupplierAccount(@RequestBody SupplierDto supplierDto) {

		Supplier supplier = supplierDtoConverter.convertToEntity(supplierDto);
		try {
			this.supplierService.signUpByAdmin(supplier);
		} catch (WrongInformationException e) {
			logger.error("Mauvaise information", e);
			return new ResponseEntity<>("The information of sign-up are not valid ! "+e.getMessage() , HttpStatus.BAD_REQUEST);
		} catch (PlayerAlreadyExistException e) {
			logger.error("Le joueur existe déjà", e);
			return new ResponseEntity<>("The player already exist !", HttpStatus.CONFLICT);
		} catch (MessagingException e) {
			logger.error("Impossible d'envoyer le mail", e);
			return new ResponseEntity<>("A error occurs when sending validation mail !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * Gets the validated QCM.
	 *
	 * @return the validated QCM
	 */
	@RequestMapping(value = "/getValidatedQCM", method = RequestMethod.GET)
	public ResponseEntity<List<QCM>> getValidatedQCM(){
		List<QCM> listQCM = adminService.getAllValidatedQCM();

		if(!listQCM.isEmpty()){
			return ResponseEntity.status(HttpStatus.OK).body(listQCM);
		} 
		logger.info("Aucun QCM trouvé");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	/**
	 * Gets the not validated QCM.
	 *
	 * @return the not validated QCM
	 */
	@RequestMapping(value = "/getNotValidatedQCM", method = RequestMethod.GET)
	public ResponseEntity<List<QCM>> getNotValidatedQCM(){
		List<QCM> listQCM = adminService.getAllNotValidatedQCM();

		if(!listQCM.isEmpty()){
			return ResponseEntity.status(HttpStatus.OK).body(listQCM);
		} 
		logger.info("Aucun QCM trouvé");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	/**
	 * Validate QCM.
	 *
	 * @param idQCM the id QCM
	 * @return the response entity
	 */
	@RequestMapping(value = "/validateQCM", method = RequestMethod.PUT)
	public ResponseEntity<String> validateQCM(@RequestParam Map<String, String> requestParams){
	
		String idQCM = requestParams.get("idQCM");
		try {
			adminService.validateQCM(Long.parseLong(idQCM));
		} catch (QCMDoesNotExistException | QCMValidationException e) {
			logger.error("Impossible to validate this QCM", e);
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	/**
	 * Delete QCM.
	 *
	 * @param idQCM the id QCM
	 * @return the response entity
	 */
	@RequestMapping(value = "/deleteQCM", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteQCM(@RequestParam Map<String, String> requestParams){
		String idQCM = requestParams.get("idQCM");
		try{
			qcmService.deleteQCM(Long.parseLong(idQCM));
		} catch (QCMDoesNotExistException e) {
			logger.error("Impossible to delete this QCM", e);
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
