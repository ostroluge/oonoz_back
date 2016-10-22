package oonoz.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Player;
import oonoz.domain.Supplier;
import oonoz.dto.converter.PlayerDtoConverter;
import oonoz.dto.converter.SupplierDtoConverter;
import oonoz.dto.model.PlayerDto;
import oonoz.dto.model.SupplierDto;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;
import oonoz.service.SupplierService;
import oonoz.util.StringResponse;


/**
 * The Class UserController.
 * 
 * Description :
 * 		The REST service which manages Player, Supplier and Admin entity.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/** The player service. */
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private SupplierService supplierService;
	
	/** The player dto converter. */
	@Autowired
	private PlayerDtoConverter playerDtoConverter;
	
	@Autowired
	private SupplierDtoConverter supplierDtoConverter;
	
	
	/**
	 * Authenticate users.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> login(HttpServletRequest request) {

        /* Getting session and then invalidating it */

        HttpSession session = request.getSession(false);

        /*if (request.isRequestedSessionIdValid() && session != null) {
            session.invalidate();
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);*/
        return new ResponseEntity<>("", HttpStatus.OK);
    }
	
	/**
	 * Signup player.
	 *
	 * @param playerDto the player dto
	 * 		The light representation of the player entity
	 * @return the response entity
	 */
	@RequestMapping(value = "/signUpPlayer", method = RequestMethod.POST)
    public ResponseEntity<StringResponse> signupPlayer(@RequestBody PlayerDto playerDto) {
		
		Player player = playerDtoConverter.convertToEntity(playerDto);
		StringResponse response = new StringResponse();
		try {
			this.playerService.signUp(player);
		} catch (WrongInformationException e) {
			logger.error(e.getMessage());
			response.setResponse("The information of sign-up are not valid !");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(response);
//			return new ResponseEntity<>("The information of sign-up are not valid ! "+e.getMessage() , HttpStatus.BAD_REQUEST);
		} catch (PlayerAlreadyExistException e) {
			logger.error(e.getMessage());
			response.setResponse("The player already exists !");
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(response);
//			return new ResponseEntity<>("The player already exist !", HttpStatus.BAD_REQUEST);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
			response.setResponse("A error occurs when sending validation mail !");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(response);
//			return new ResponseEntity<>("A error occurs when sending validation mail !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setResponse("Player created !");
		return ResponseEntity.status(HttpStatus.OK)
				.body(response);
//		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	/**
	 * Signup supplier.
	 *
	 * @param supplierDto the supplier dto
	 * 		The light representation of the supplier entity
	 * @return the response entity
	 */
	@RequestMapping(value = "/signUpSupplier", method = RequestMethod.POST)
    public ResponseEntity<String> signupSupplier(@RequestBody SupplierDto supplierDto) {
		
		Supplier supplier = supplierDtoConverter.convertToEntity(supplierDto);
		try {
			this.supplierService.signUp(supplier);
		} catch (WrongInformationException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("The information of sign-up are not valid ! "+e.getMessage() , HttpStatus.BAD_REQUEST);
		} catch (PlayerAlreadyExistException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("The player already exist !", HttpStatus.CONFLICT);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("A error occurs when sending validation mail !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	/**
     * Rest service receiving a email and a token.
     * If the token is right I will activate the account of the user matching the mail.
     * @param mail The email of the user to activate.
     * @param hash The token to verify the link.
     * @return A response containing a string with the answer.
     */
    @RequestMapping(value = "/validationMail", method = RequestMethod.GET)
    public ResponseEntity<String> validationMail(@RequestParam(value = "mail", defaultValue = "") String mail, @RequestParam(value = "cle", defaultValue = "0") String hash) {

        try {
        	playerService.validationMail(mail,hash);
        } catch (PlayerNotExistException e) {
        	logger.error(e.getMessage());
            return new ResponseEntity<>("The player with this mail does not exist !", HttpStatus.BAD_REQUEST);
        } catch (WrongInformationException e) {
        	logger.error(e.getMessage());
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
        
        return new ResponseEntity<>("", HttpStatus.OK);

    }
    
    /**
     * Rest service receiving a email and a token.
     * If the token is right I will activate the account of the user matching the mail.
     * @param mail The email of the user to activate.
     * @param hash The token to verify the link.
     * @return A response containing a string with the answer.
     */
    @RequestMapping(value = "/generatePassword", method = RequestMethod.POST)
    public ResponseEntity<String> generatePassword(@RequestBody String mail) {

        try {
        	playerService.generatePassword(mail);
        } catch (PlayerNotExistException e) {
        	logger.error(e.getMessage());
            return new ResponseEntity<>("The player with this mail does not exist !", HttpStatus.BAD_REQUEST);
        } catch (WrongInformationException e) {
        	logger.error(e.getMessage());
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
        
        return new ResponseEntity<>("", HttpStatus.OK);

    }

}
