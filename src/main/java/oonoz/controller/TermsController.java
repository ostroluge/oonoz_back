package oonoz.controller;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oonoz.dto.model.TermDto;
import oonoz.util.StringResponse;

/**
 * The Class TermsController.
 */
@RestController
@RequestMapping("/terms")
public class TermsController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(TermsController.class);

	/** The Constant URL_TERMS_PLAYER. */
	private static final String URL_TERMS_PLAYER = "terms_of_service_player.txt";

	/** The Constant URL_TERMS_SUPPLIER. */
	private static final String URL_TERMS_SUPPLIER = "terms_of_service_supplier.txt";

	/**
	 * Gets the terms player.
	 *
	 * @return the terms player
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = "/player", method = RequestMethod.GET) 
	public ResponseEntity<StringResponse> getTermsPlayer() throws FileNotFoundException, IOException {
		StringResponse response = new StringResponse();
		String content;

		try (FileInputStream inputStream = new FileInputStream(URL_TERMS_PLAYER)) {
			content = IOUtils.toString(inputStream, "UTF-8");
		} 

		if (!TextUtils.isEmpty(content)) {
			response.setResponse(content);
			return ResponseEntity.status(HttpStatus.OK)
					.body(response);
		} else {
			response.setResponse(null);
			logger.debug("The content of player's terms is null");
		}

		logger.error("Get player's terms failed");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
	}

	/**
	 * Gets the terms supplier.
	 *
	 * @return the terms supplier
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = "/supplier", method = RequestMethod.GET) 
	public ResponseEntity<StringResponse> getTermsSupplier() throws FileNotFoundException, IOException {
		StringResponse response = new StringResponse();
		String content;

		try (FileInputStream inputStream = new FileInputStream(URL_TERMS_SUPPLIER)) {
			content = IOUtils.toString(inputStream, "UTF-8");
		} 

		if (!TextUtils.isEmpty(content)) {
			response.setResponse(content);
			return ResponseEntity.status(HttpStatus.OK)
					.body(response);
		} else {
			response.setResponse(null);
			logger.debug("The content of supplier's terms is null");
		}

		logger.error("Get supplier's terms failed");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
	}

	/**
	 * Post terms player.
	 *
	 * @param termDto the term dto
	 * @return the response entity
	 */
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	public ResponseEntity<StringResponse> postTermsPlayer(@RequestBody TermDto termDto) {
		StringResponse response = new StringResponse();
		BufferedWriter bw = null;

		try {
			if (termDto != null) {
				if (!TextUtils.isEmpty(termDto.getContent())) {
					bw = new BufferedWriter(new FileWriter(URL_TERMS_PLAYER, false));
					bw.write(termDto.getContent());
					bw.flush();
					bw.close();
					response.setResponse("Ok");
					return ResponseEntity.status(HttpStatus.OK)
							.body(response);
				}
				logger.debug("termDto.getContent() == null");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(null);
			} else {
				logger.debug("termDto == null");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(null);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
	
	/**
	 * Post terms supplier.
	 *
	 * @param termDto the term dto
	 * @return the response entity
	 */
	@RequestMapping(value = "/supplier", method = RequestMethod.POST)
	public ResponseEntity<StringResponse> postTermsSupplier(@RequestBody TermDto termDto) {
		StringResponse response = new StringResponse();
		BufferedWriter bw = null;

		try {
			if (termDto != null) {
				if (!TextUtils.isEmpty(termDto.getContent())) {
					bw = new BufferedWriter(new FileWriter(URL_TERMS_SUPPLIER, false));
					bw.write(termDto.getContent());
					bw.flush();
					bw.close();
					response.setResponse("Ok");
					return ResponseEntity.status(HttpStatus.OK)
							.body(response);
				}
				logger.debug("termDto.getContent() == null");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(null);
			} else {
				logger.debug("termDto == null");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(null);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
}
