package com.controller.aviokompanija;

import com.dto.aviokompanija.*;
import com.model.aviokompanija.Ocena;
import com.model.user.User;
import com.service.UserService;
import com.service.aviokompanija.AviokompanijaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/aviokompanija")
public class AviokompanijaController {

	@Autowired
	private AviokompanijaService aviokompanijaService;


	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca sve aviokompanije", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<AviokompanijaDTO>> getAll(){
			return new ResponseEntity<>(aviokompanijaService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca aviokompaniju sa prosledjenim id-em", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = AviokompanijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<AviokompanijaDTO> findById(@PathVariable(value="id") Long id){
		return new ResponseEntity<>(aviokompanijaService.findById(id),HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira aviokompaniju", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = AviokompanijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AviokompanijaDTO> create(@RequestBody AviokompanijaDTO aviokompanijaDTO){
		return new ResponseEntity<>(aviokompanijaService.create(aviokompanijaDTO), HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update aviokompanije", httpMethod = "PUT", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = AviokompanijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<AviokompanijaDTO> update(@RequestBody AviokompanijaDTO aviokompanijaDTO){
		return new ResponseEntity<>(aviokompanijaService.update(aviokompanijaDTO), HttpStatus.OK);
	}
	 
	 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	 @ApiOperation(value = "Brise aviokompaniju", httpMethod = "DELETE")
	 @ApiResponses(value = {
			 @ApiResponse(code = 200, message = "OK"),
			 @ApiResponse(code = 204, message = "No Content"),
			 @ApiResponse(code = 400, message = "Bad Request")
	 })
	 public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
		aviokompanijaService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/aerodromi", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Vraca sve aerodrome prosledjene avikompanije", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = AerodromDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<AerodromDTO>> aerodromi(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(aviokompanijaService.aerodromi(id),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/aerodrom", method=RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Kreira aerodrom za aviokompaniju", httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = AerodromDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<AerodromDTO> napraviAerodrom(@PathVariable(value = "id") Long id,
															 @RequestBody AerodromDTO aerodromDTO){
		return new ResponseEntity<>(aviokompanijaService.napraviAerodrom(id,aerodromDTO),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/destinacije", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Vraca sve destinacije na kojima aviokompanija posluje", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = LokacijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<LokacijaDTO>> destinacije(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(aviokompanijaService.destinacije(id),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/prtljag", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Vraca sve vrste prtljaga u okviru kompanije", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = PrtljagDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<PrtljagDTO>> prtljag(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(aviokompanijaService.prtljag(id),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/prtljag", method=RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Kreira prtljag za aviokompaniju", httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = PrtljagDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<PrtljagDTO> napraviPrtljag(@PathVariable(value = "id") Long id,
													   @RequestBody PrtljagDTO prtljagDTO){
		return new ResponseEntity<>(aviokompanijaService.napraviPrtljag(id,prtljagDTO),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/letovi", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Vraca sve letove u okviru kompanije", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = LetDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<LetDTO>> letovi(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(aviokompanijaService.letovi(id),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/konfiguracije", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Vraca sve konfiguracije u okviru kompanije", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = KonfiguracijaLetaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<KonfiguracijaLetaDTO>> konfiguracije(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(aviokompanijaService.konfiguracije(id),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/konfiguracija", method=RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Kreira konfiguraciju leta za aviokompaniju", httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = KonfiguracijaLetaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<KonfiguracijaLetaDTO> napraviKonfiguraciju(@PathVariable(value = "id") Long id,
													 @RequestBody KonfiguracijaLetaDTO konfiguracijaLetaDTO){
		return new ResponseEntity<>(aviokompanijaService.napraviKonfiguraciju(id,konfiguracijaLetaDTO),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/dodatne_usluge", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Vraca sve dodatne usluge u okviru kompanije", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = DodatnaUslugaAviokompanijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<DodatnaUslugaAviokompanijaDTO>> dodatneUsluge(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(aviokompanijaService.dodatneUsluge(id),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/dodatna_usluga", method=RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Kreira konfiguraciju leta za aviokompaniju", httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = DodatnaUslugaAviokompanijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<DodatnaUslugaAviokompanijaDTO> napraviDodatnuUslugu(@PathVariable(value = "id") Long id,
																	 @RequestBody DodatnaUslugaAviokompanijaDTO dodatnaUslugaAviokompanijaDTO){
		return new ResponseEntity<>(aviokompanijaService.napraviDodatnuUslugu(id,dodatnaUslugaAviokompanijaDTO),HttpStatus.OK);
	}


	@RequestMapping(value = "{id}/postavi_lokaciju", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira let za prosledjeni aerodrom", httpMethod = "PUT", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = LokacijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<LokacijaDTO> postaviLokaciju(@PathVariable(value = "id") Long id, @RequestBody LokacijaDTO lokacija ){
		return new ResponseEntity<>(aviokompanijaService.postaviLokaciju(id, lokacija.getId()), HttpStatus.OK);
	}


}
