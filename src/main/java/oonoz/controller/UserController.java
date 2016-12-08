package oonoz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import oonoz.domain.Theme;
import oonoz.dto.converter.PlayerDtoConverter;
import oonoz.dto.converter.QCMDtoConverter;
import oonoz.dto.converter.QuestionDtoConverter;
import oonoz.dto.converter.SupplierDtoConverter;
import oonoz.dto.model.PlayerDto;
import oonoz.dto.model.QCMDto;
import oonoz.dto.model.SupplierDto;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotActiveException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;
import oonoz.service.QCMService;
import oonoz.service.SubThemeService;
import oonoz.service.SupplierService;
import oonoz.service.ThemeService;
import oonoz.util.CheckThemeInformation;
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

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private QCMService qcmService;

//	@Autowired
	
	@Autowired
	private ThemeService themeService;

	@Autowired
	private QCMService subThemeService;

	@Autowired
	private CheckThemeInformation checkThemeInformation;

	/** The player dto converter. */
	@Autowired
	private PlayerDtoConverter playerDtoConverter;

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
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<String> login(Authentication authentication, HttpServletRequest request) {

		/* Getting session and then invalidating it */

		@SuppressWarnings("unused")
		HttpSession session = request.getSession(false);
		// HttpSession session = request.getSession(false);
		// Player player = playerService.getPlayerByUsername((((UserDetails)
		// authentication.getPrincipal()).getUsername()));
		// HttpSession session = request.getSession();
		// session.setAttribute("USER", player);

		/*
		 * if (request.isRequestedSessionIdValid() && session != null) {
		 * session.invalidate(); return new ResponseEntity<>("", HttpStatus.OK);
		 * } return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		 */
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
			// return new ResponseEntity<>("The information of sign-up are not
			// valid ! "+e.getMessage() , HttpStatus.BAD_REQUEST);
		} catch (PlayerAlreadyExistException e) {
			logger.error("This player already exist", e);
			response.setResponse("The player already exists !");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			// return new ResponseEntity<>("The player already exist !",
			// HttpStatus.BAD_REQUEST);
		} catch (MessagingException e) {
			logger.error("Impossible to send validation mail", e);
			response.setResponse("A error occurs when sending validation mail !");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			// return new ResponseEntity<>("A error occurs when sending
			// validation mail !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setResponse("Player created !");
		return ResponseEntity.status(HttpStatus.OK).body(response);
		// return new ResponseEntity<>("", HttpStatus.OK);
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
	 * List of Supplier QCM
	 *
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@RequestMapping(value = "/getSupplierQCM", method = RequestMethod.GET)
	public ResponseEntity<List<QCM>> getSupplierQCM(Authentication authentication) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		// String playerUsername = auth.getName();
		String playerUsername = ((UserDetails) authentication.getPrincipal()).getUsername();
		Player p = playerService.getPlayerByUsername(playerUsername);
		List<QCM> QcmList = qcmService.getSupplierQCM(p.getIdPlayer());
		if (QcmList != null) {
			return ResponseEntity.status(HttpStatus.OK).body(QcmList);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	

}
