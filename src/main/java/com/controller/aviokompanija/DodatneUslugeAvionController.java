package com.controller.aviokompanija;

import com.dto.aviokompanija.DodatnaUslugaAviokompanijaDTO;
import com.service.aviokompanija.DodatneUslugeAvionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/dodatne_usluge")
public class DodatneUslugeAvionController {
	@Autowired
	private DodatneUslugeAvionService dodatneUslugeAvionService;

	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca sve dodatne usluge", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<DodatnaUslugaAviokompanijaDTO>> getAll(){
		return new ResponseEntity<>(dodatneUslugeAvionService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca dodatnu uslugu sa prosledjenim id-em", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = DodatnaUslugaAviokompanijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<DodatnaUslugaAviokompanijaDTO> findById(@PathVariable(value="id") Long id){
		return new ResponseEntity<>(dodatneUslugeAvionService.findById(id),HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira dodatnu uslugu", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = DodatnaUslugaAviokompanijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<DodatnaUslugaAviokompanijaDTO> create(@RequestBody DodatnaUslugaAviokompanijaDTO dodatnaUslugaAviokompanijaDTO){
		return new ResponseEntity<>(dodatneUslugeAvionService.create(dodatnaUslugaAviokompanijaDTO), HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update dodatne usluge", httpMethod = "PUT", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = DodatnaUslugaAviokompanijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<DodatnaUslugaAviokompanijaDTO> update(@RequestBody DodatnaUslugaAviokompanijaDTO dodatnaUslugaAviokompanijaDTO){
		return new ResponseEntity<>(dodatneUslugeAvionService.update(dodatnaUslugaAviokompanijaDTO), HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Brise dodatnu uslugu", httpMethod = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
		dodatneUslugeAvionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
