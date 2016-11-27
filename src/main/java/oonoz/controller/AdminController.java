package oonoz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Supplier;
import oonoz.service.SupplierService;
import oonoz.util.StringResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SupplierService supplierService;

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
	 * Authenticate users.
	 *
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@RequestMapping(value = "/refuseSupplierRequest", method=RequestMethod.DELETE)
	public ResponseEntity<StringResponse> refuseSupplierRequest(@RequestParam(value = "idPlayer", defaultValue = "") Long idPlayer){
//	public ResponseEntity<List<Supplier>> refuseSupplierRequest(HttpServletRequest request) {
		supplierService.refuseSupplierRequest(idPlayer);
		StringResponse response = new StringResponse();
		response.setResponse("La demande a été refusée");
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * 
	 * @param idPlayer
	 * @return
	 */
	@RequestMapping(value = "/acceptSupplierRequest", method=RequestMethod.POST)
	public ResponseEntity<StringResponse> acceptSupplierRequest(@RequestParam(value = "idPlayer", defaultValue = "") Long idPlayer){
		supplierService.acceptSupplierRequest(idPlayer);
		StringResponse response = new StringResponse();
		response.setResponse("La demande a été acceptée");
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}


}
