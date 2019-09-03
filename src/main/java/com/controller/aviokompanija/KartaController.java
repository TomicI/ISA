package com.controller.aviokompanija;

import com.dto.RezervacijaDTO;
import com.dto.aviokompanija.KartaDTO;
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

	@Autowired
	private UserService userService;

	@RequestMapping(value="/brze_rezervacije", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vraca sve brze rezervacije", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = List.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<List<KartaDTO>> brzeRezervacije(){
		return new ResponseEntity<>(kartaService.brzeRezervacije(), HttpStatus.OK);
	}


	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Kreira kartu", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = RezervacijaDTO.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<RezervacijaDTO> create(@RequestBody List<Long> sedista, Principal user){
		Optional<User> u=this.userService.findByUsername(user.getName());
		return new ResponseEntity<>(kartaService.create(u.get().getId(), sedista), HttpStatus.CREATED);
	}
}
