package oonoz.controller;

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
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Player;
import oonoz.dto.converter.PlayerDtoConverter;
import oonoz.dto.model.PlayerDto;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;


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
	
	/** The player dto converter. */
	@Autowired
	private PlayerDtoConverter playerDtoConverter;
	
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
	 * @return the response entity
	 */
	@RequestMapping(value = "/signupPlayer", method = RequestMethod.POST)
    public ResponseEntity<String> signupPlayer(@RequestBody PlayerDto playerDto) {
		
		Player player=playerDtoConverter.convertToEntity(playerDto);
		try {
			playerService.signUp(player);
		} catch (WrongInformationException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("The information of sign-up are not valid ! "+e.getMessage() , HttpStatus.BAD_REQUEST);
		} catch (PlayerAlreadyExistException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("The player already exist !", HttpStatus.BAD_REQUEST);
		}
		 return new ResponseEntity<>("", HttpStatus.OK);
	}

}
