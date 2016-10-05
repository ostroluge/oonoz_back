package oonoz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
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

}
