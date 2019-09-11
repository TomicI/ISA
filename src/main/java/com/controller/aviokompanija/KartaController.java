package com.controller.aviokompanija;

import com.dto.RezervacijaDTO;
import com.dto.aviokompanija.KartaDTO;
import com.dto.aviokompanija.SedisteDTO;
import com.model.user.User;
import com.service.UserService;
import com.service.aviokompanija.KartaService;
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
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/karta")
public class KartaController {
	@Autowired
	private KartaService kartaService;


	@RequestMapping(value="/brze_rezervacije/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca sve brze rezervacije", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<KartaDTO>> brzeRezervacije(@PathVariable Long id){
		return new ResponseEntity<>(kartaService.brzeRezervacije(id), HttpStatus.OK);
	}


	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira kartu", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = RezervacijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<RezervacijaDTO> create(@RequestBody List<SedisteDTO> sedista, Principal user){
		return new ResponseEntity<>(kartaService.create(user.getName(), sedista), HttpStatus.CREATED);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ApiOperation(value = "Brise kartu", httpMethod = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = RezervacijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<String> delete(@PathVariable Long id, Principal user){
		kartaService.delete(user.getName(), id);
		return new ResponseEntity<>( HttpStatus.OK);
	}


	@RequestMapping(value="/brzaRez/{sediste}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira brzu rezervaciju", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = RezervacijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<KartaDTO> createBR(@PathVariable Long sediste, @RequestBody Double popust, Principal user){
		return new ResponseEntity<>(kartaService.createBR(user.getName(), sediste, popust), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/sedista/{id}",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca sva sedista", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<SedisteDTO>> getSedista(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(kartaService.getSedista(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca kartu", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<KartaDTO> getKarta(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(kartaService.getKarta(id), HttpStatus.OK);
	}


	@RequestMapping(value="/rezervisanje", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira rezervaciju uz pomoc brze rezervacije", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = RezervacijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<RezervacijaDTO> createBR(@RequestBody KartaDTO kartaDTO, Principal user){
		return new ResponseEntity<>(kartaService.createRezB(user.getName(), kartaDTO.getId()), HttpStatus.CREATED);
	}
}
