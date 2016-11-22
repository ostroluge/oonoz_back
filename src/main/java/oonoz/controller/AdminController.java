package oonoz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;
import oonoz.service.SupplierService;
import oonoz.util.FilteredSearch;

@RestController
@RequestMapping("/admin")
public class AdminController {

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
	
	@RequestMapping(value = "/filteredSearch", method = RequestMethod.POST)
    public ResponseEntity<Page<PlayerDto>> filteredSearch(@RequestBody FilteredSearch filteredSearch) throws WrongInformationException{
    	    	    	
    	 return new ResponseEntity<>(playerService.filteredSearch(filteredSearch), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updatePlayer", method = RequestMethod.POST)
    public ResponseEntity<String> updateUser(@RequestBody PlayerDto playerDto) throws WrongInformationException, PlayerNotExistException{
    	    	    	    	
    	Player player=playerDtoConverter.convertToEntity(playerDto);
    	
    	//TODO modifier la conversion pour l'id
    	player.setId(playerDto.getIdPlayer());
    	playerService.updatePlayer(player);
    	
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updateSupplier", method = RequestMethod.POST)
    public ResponseEntity<String> updateSupplier( SupplierDto supplierDto) throws WrongInformationException, PlayerNotExistException{
    	    	    	
    	Supplier supplier=supplierDtoConverter.convertToEntity(supplierDto);
    	supplierService.updateSupplier(supplier);
    	/*Player player=playerDtoConverter.convertToEntity(playerDto);
    	playerService.updatePlayer(player);
    	
    	//TODO modifier la conversion pour l'id
    	player.setId(playerDto.getIdPlayer());
    	playerService.updatePlayer(player);*/
    	 return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestParam(value = "idPlayer", defaultValue = "") Long idPlayer) throws PlayerNotExistException{
    	
    	playerService.deletePlayer(idPlayer);
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
}
