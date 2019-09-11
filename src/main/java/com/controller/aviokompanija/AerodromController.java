package com.controller.aviokompanija;

import com.dto.aviokompanija.AerodromDTO;
import com.dto.aviokompanija.LetDTO;
import com.dto.aviokompanija.LokacijaDTO;
import com.service.aviokompanija.AerodromService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/aerodrom")
public class AerodromController {

	@Autowired
	private AerodromService aerodromService;

	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca sve aerodrome", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<AerodromDTO>> getAll(){
		return new ResponseEntity<>(aerodromService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca aerodrom sa prosledjenim id-em", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = AerodromDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<AerodromDTO> findById(@PathVariable(value="id") Long id){
		return new ResponseEntity<>(aerodromService.findById(id),HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira aerodrom", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = AerodromDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<AerodromDTO> create(@RequestBody AerodromDTO aerodromDTO, Principal user){
		return new ResponseEntity<>(aerodromService.create(aerodromDTO, user.getName()), HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update aerodrom", httpMethod = "PUT", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = AerodromDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<AerodromDTO> update(@RequestBody AerodromDTO aerodromDTO, Principal user){
		return new ResponseEntity<>(aerodromService.update(aerodromDTO, user.getName()), HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Brise aerodrom", httpMethod = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
		aerodromService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/letovi", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Vraca sve letove u okviru aerodroma", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = LetDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<LetDTO>> letovi(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(aerodromService.letovi(id),HttpStatus.OK);
	}

	@RequestMapping(value = "{id}/let", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira let za prosledjeni aerodrom", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = LetDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<LetDTO> let(@PathVariable(value = "id") Long id, @RequestBody LetDTO letDTO){
		return new ResponseEntity<>(aerodromService.let(id, letDTO), HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}/postavi_lokaciju/{location_id}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira let za prosledjeni aerodrom", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = LokacijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<LokacijaDTO> postaviLokaciju(@PathVariable(value = "id") Long id, @PathVariable(value = "location_id") Long lokacija ){
		return new ResponseEntity<>(aerodromService.postaviLokaciju(id, lokacija), HttpStatus.OK);
	}

 
}
