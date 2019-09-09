package com.controller.aviokompanija;

import com.dto.aviokompanija.LetDTO;
import com.dto.aviokompanija.OcenaDTO;
import com.dto.aviokompanija.SedisteDTO;
import com.model.aviokompanija.Let;
import com.service.aviokompanija.LetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/let")
public class LetController {

	@Autowired
	private LetService letService;


	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca sve letove", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<LetDTO>> getAll(Principal user){
		return new ResponseEntity<>(letService.getAll(user.getName()), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca let sa prosledjenim id-em", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Let.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<LetDTO> findById(@PathVariable(value="id") Long id){
		return new ResponseEntity<>(letService.findById(id),HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira let", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Let.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<LetDTO> create(@RequestBody LetDTO letDTO){
		return new ResponseEntity<>(letService.create(letDTO), HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update let", httpMethod = "PUT", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Let.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<LetDTO> update(@RequestBody LetDTO letDTO){
		return new ResponseEntity<>(letService.update(letDTO), HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Brise let", httpMethod = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
		letService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/oceni/{user_id}/{ocena}", method=RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "User sa id-em user_id ocenjuje let sa id-em id", httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = OcenaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<OcenaDTO> oceni(@PathVariable(value = "id") Long id,
										  @PathVariable(value = "user_id") Long userId,
										  @PathVariable(value = "ocena") Integer ocena){
		return new ResponseEntity<>(letService.oceni(id,userId,ocena),HttpStatus.OK);
	}

	@RequestMapping(value="/pretraga", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca sve letove", httpMethod = "PUT", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<LetDTO>> pretraga(@RequestBody LetDTO letDTO){

			return new ResponseEntity<>(letService.pretraga(letDTO), HttpStatus.OK);

	}


	@RequestMapping(value = "/sedista/{idL}",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca sva sedista", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<SedisteDTO>> segmenti( @PathVariable(value = "idL") Long idL){
		return new ResponseEntity<>(letService.getSedista(idL), HttpStatus.OK);
	}
}
