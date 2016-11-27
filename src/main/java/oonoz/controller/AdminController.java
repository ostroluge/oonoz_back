package oonoz.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

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
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;
import oonoz.service.SupplierService;
import oonoz.util.StringResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
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
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@RequestMapping(value = "/refuseSupplierRequest", method = RequestMethod.DELETE)
	public ResponseEntity<StringResponse> refuseSupplierRequest(
			@RequestParam(value = "idPlayer", defaultValue = "") Long idPlayer) {
		// public ResponseEntity<List<Supplier>>
		// refuseSupplierRequest(HttpServletRequest request) {
		supplierService.refuseSupplierRequest(idPlayer);
		StringResponse response = new StringResponse();
		response.setResponse("La demande a été refusée");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Accept supplier request
	 * 
	 * @param idPlayer
	 * @return
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
	 * 
	 * @param playerDto
	 * @return
	 */
	@RequestMapping(value = "/createPlayerAccount", method = RequestMethod.POST)
	public ResponseEntity<StringResponse> createPlayerAccount(@RequestBody PlayerDto playerDto) {

		Player player = playerDtoConverter.convertToEntity(playerDto);
		StringResponse response = new StringResponse();
		try {
			this.playerService.signUp(player);
		} catch (WrongInformationException e) {
			logger.error(e.getMessage());
			response.setResponse("The information of sign-up are not valid !");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			// return new ResponseEntity<>("The information of sign-up are not
			// valid ! "+e.getMessage() , HttpStatus.BAD_REQUEST);
		} catch (PlayerAlreadyExistException e) {
			logger.error(e.getMessage());
			response.setResponse("The player already exists !");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			// return new ResponseEntity<>("The player already exist !",
			// HttpStatus.BAD_REQUEST);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
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

}
