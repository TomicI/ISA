package com.controller.aviokompanija;

import com.dto.aviokompanija.AerodromDTO;
import com.dto.aviokompanija.AviokompanijaDTO;
import com.dto.aviokompanija.LetDTO;
import com.dto.aviokompanija.LokacijaDTO;
import com.service.aviokompanija.LokacijaService;
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
@RequestMapping(value="api/lokacija")
public class LokacijaController {

    @Autowired
    private LokacijaService lokacijaService;

    @RequestMapping(method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve lokacije", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<LokacijaDTO>> getAll(){
        return new ResponseEntity<>(lokacijaService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca lokaciju sa prosledjenim id-em", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LokacijaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<LokacijaDTO> findById(@PathVariable(value="id") Long id){
        return new ResponseEntity<>(lokacijaService.findById(id),HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreira lokaciju", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LokacijaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<LokacijaDTO> create(@RequestBody LokacijaDTO lokacijaDTO){
        return new ResponseEntity<>(lokacijaService.create(lokacijaDTO), HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update lokacije", httpMethod = "PUT", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LokacijaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<LokacijaDTO> update(@RequestBody LokacijaDTO lokacijaDTO){
        return new ResponseEntity<>(lokacijaService.update(lokacijaDTO), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Brise lokaciju", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
        lokacijaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/letovi",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve letove na datoj lokaciji", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<LetDTO>> sviLetovi(@PathVariable(value="id") Long id){
        return new ResponseEntity<>(lokacijaService.sviLetovi(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/aviokompanije",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve aviokompanije na datoj lokaciji", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<AviokompanijaDTO>> sveAviokompanije(@PathVariable(value="id") Long id){
        return new ResponseEntity<>(lokacijaService.sveAviokompanije(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/aerodromi",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve aerodrome na datoj lokaciji", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<AerodromDTO>> sviAerodromi(@PathVariable(value="id") Long id){
        return new ResponseEntity<>(lokacijaService.sviAerodromi(id),HttpStatus.OK);
    }
}
