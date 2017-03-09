package oonoz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Player;
import oonoz.domain.QCM;
import oonoz.domain.Supplier;
import oonoz.dto.converter.PlayerDtoConverter;
import oonoz.dto.converter.QCMDtoConverter;
import oonoz.dto.converter.QuestionDtoConverter;
import oonoz.dto.converter.SupplierDtoConverter;
import oonoz.dto.model.PlayerDto;
import oonoz.dto.model.SupplierDto;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotActiveException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;
import oonoz.service.QCMPlayService;
import oonoz.service.QCMService;
import oonoz.service.SupplierService;
import oonoz.util.StringResponse;

/**
 * The Class UserController.
 * 
 * Description : The REST service which manages Player, Supplier and Admin
 * entity.
 */
@RestController
@RequestMapping("/user")
public class UserController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/** The player service. */
	@Autowired
	private PlayerService playerService;

	/** The supplier service. */
	@Autowired
	private SupplierService supplierService;

	/** The qcm service. */
	@Autowired
	private QCMService qcmService;
	
	/** The qcm service. */
	@Autowired
	private QCMPlayService qcmPlayService;


	/** The player dto converter. */
	@Autowired
	private PlayerDtoConverter playerDtoConverter;

	/** The supplier dto converter. */
	@Autowired
	private SupplierDtoConverter supplierDtoConverter;

	/** The qcm dto converter. */
	@Autowired
	QCMDtoConverter qcmDtoConverter;

	/** The question dto converter. */
	@Autowired
	QuestionDtoConverter questionDtoConverter;

	/**
	 * Authenticate users.
	 *
	 * @param authentication the authentication
	 * @param request            the request
	 * @return the response entity
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<String> login(Authentication authentication, HttpServletRequest request) {

		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * Signup player.
	 *
	 * @param playerDto
	 *            the player dto The light representation of the player entity
	 * @return the response entity
	 */
	@RequestMapping(value = "/signUpPlayer", method = RequestMethod.POST)
	public ResponseEntity<StringResponse> signupPlayer(@RequestBody PlayerDto playerDto) {

		Player player = playerDtoConverter.convertToEntity(playerDto);
		StringResponse response = new StringResponse();

		try {
			this.playerService.signUp(player);
		} catch (WrongInformationException e) {
			logger.error("Wrong information", e);
			response.setResponse("The information of sign-up are not valid !");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} catch (PlayerAlreadyExistException e) {
			logger.error("This player already exist", e);
			response.setResponse("The player already exists !");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		} catch (MessagingException e) {
			logger.error("Impossible to send validation mail", e);
			response.setResponse("A error occurs when sending validation mail !");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		response.setResponse("Player created !");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Signup supplier.
	 *
	 * @param supplierDto
	 *            the supplier dto The light representation of the supplier
	 *            entity
	 * @return the response entity
	 */
	@RequestMapping(value = "/signUpSupplier", method = RequestMethod.POST)
	public ResponseEntity<String> signupSupplier(@RequestBody SupplierDto supplierDto) {

		Supplier supplier = supplierDtoConverter.convertToEntity(supplierDto);
		try {
			this.supplierService.signUp(supplier);
		} catch (WrongInformationException e) {
			logger.error("Wrong information", e);
			return new ResponseEntity<>("The information of sign-up are not valid ! " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		} catch (PlayerAlreadyExistException e) {
			logger.error("Supplier already exist", e);
			return new ResponseEntity<>("The player already exist !", HttpStatus.CONFLICT);
		} catch (MessagingException e) {
			logger.error("Impossible to send validation mail", e);
			return new ResponseEntity<>("A error occurs when sending validation mail !",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * Rest service receiving a email and a token. If the token is right I will
	 * activate the account of the user matching the mail.
	 * 
	 * @param mail
	 *            The email of the user to activate.
	 * @param hash
	 *            The token to verify the link.
	 * @return A response containing a string with the answer.
	 */
	@RequestMapping(value = "/validationMail", method = RequestMethod.GET)
	public ResponseEntity<String> validationMail(@RequestParam(value = "mail", defaultValue = "") String mail,
			@RequestParam(value = "key", defaultValue = "0") String hash) {

		try {
			playerService.validationMail(mail, hash);
		} catch (PlayerNotExistException e) {
			logger.error("This player doesn't exist", e);
			return new ResponseEntity<>("The player with this mail does not exist !", HttpStatus.BAD_REQUEST);
		} catch (WrongInformationException e) {
			logger.error("Wrong information", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("", HttpStatus.OK);

	}

	/*
	 * Rest service receiving a email and a token. If the token is right I will
	 * activate the account of the user matching the mail.
	 * @param playerDto the player dto
	 * @return A response containing a string with the answer.
	 */
	@RequestMapping(value = "/generatePassword", method = RequestMethod.POST)
	public ResponseEntity<String> generatePassword(@RequestBody PlayerDto playerDto) {

		try {
			playerService.generatePassword(playerDto.getMail());
		} catch (PlayerNotExistException e) {
			logger.error("Player doesn't exist", e);
			return new ResponseEntity<>("", HttpStatus.OK);
		} catch (WrongInformationException e) {
			logger.error("Wrong Information exception", e);
			return new ResponseEntity<>("", HttpStatus.OK);
		} catch (MessagingException e) {
			logger.error("Impossible to send mail", e);
			return new ResponseEntity<>("A internal error occurs !", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (PlayerNotActiveException e) {
			logger.error("This player is not active", e);
			return new ResponseEntity<>("", HttpStatus.OK);
		}

		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * List of Supplier QCM.
	 *
	 * @param authentication the authentication
	 * @return the response entity
	 */
	@RequestMapping(value = "/getSupplierQCM", method = RequestMethod.GET)
	public ResponseEntity<List<QCM>> getSupplierQCM(Authentication authentication) {
		Player p = getUserFromAuthentication(authentication);
		List<QCM> qcmList = qcmService.getSupplierQCM(p.getIdPlayer());
		if (qcmList != null) {
			return ResponseEntity.status(HttpStatus.OK).body(qcmList);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	/**
	 * Gets the suppliers.
	 *
	 * @return the suppliers
	 */
	@RequestMapping(value="/suppliers", method = RequestMethod.GET)
	public ResponseEntity<List<Supplier>> getSuppliers() {
		List<Supplier> suppliers = supplierService.getSuppliers();
		return ResponseEntity.status(HttpStatus.OK)
				.body(suppliers);
	}
	
	/**
	 * Get player from Authentication
	 * 
	 * @param authentication
	 * @return
	 * @throws PlayerNotExistException
	 */
	private Player getUserFromAuthentication(Authentication authentication) {
		try {
			return playerService.getPlayerByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
		} catch (PlayerNotExistException e) {
			logger.error("Player does not exist !", e);
			return null;
		}

	}
	
	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public ResponseEntity<Player> getProfil(Authentication authentication) {
		//PlayerDto  p = playerDtoConverter.convertToDto(getUserFromAuthentication(authentication));
		Player p =getUserFromAuthentication(authentication);
		if (p != null) {
			return ResponseEntity.status(HttpStatus.OK).body(p);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
	@RequestMapping(value = "/requestSupplierStatus", method = RequestMethod.GET)
    public ResponseEntity<String> requestSupplierStatus(@RequestParam(value = "idPlayer", defaultValue = "") Long idPlayer) throws PlayerNotExistException{
    	try{
    		if(idPlayer!=null)
    			playerService.requestSupplierStatus(idPlayer);
    	}
    	catch(PlayerNotExistException e){
    		logger.error("The user does not exist !",e);
    		return new ResponseEntity<>("The user does not exist !", HttpStatus.BAD_REQUEST);
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
	 * 
	 * @param authentication : player credential
	 * @return
	 * @throws PlayerNotExistException
	 */
	@RequestMapping(value = "/stat", method = RequestMethod.GET)
    public ResponseEntity<List<Long>> getStat(Authentication authentication) {
    	
		Player p = getUserFromAuthentication(authentication);
		
		Long qcmTotal  = qcmPlayService.getQcmPlayed(p.getIdPlayer());
		Long qcmLost = qcmPlayService.getQcmWon(p.getIdPlayer());
		Long qcmMean = qcmPlayService.getMean(p.getIdPlayer());
		Long qcmComment = (long) qcmPlayService.qcmComment(p.getIdPlayer());
		
		List<Long> allStat = new ArrayList<Long>();
    	
		allStat.add(qcmTotal);
		allStat.add(qcmLost);
		allStat.add(qcmMean);
		allStat.add(qcmComment);
		return ResponseEntity.status(HttpStatus.OK).body(allStat);
    	
		//return new ResponseEntity<>("", HttpStatus.OK);
    }
	

	
	
}
