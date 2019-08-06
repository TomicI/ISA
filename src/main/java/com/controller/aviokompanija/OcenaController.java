package com.controller.aviokompanija;

import com.dto.aviokompanija.OcenaDTO;
import com.service.aviokompanija.OcenaService;
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
@RequestMapping(value="api/ocena")
public class OcenaController {

    @Autowired
    private OcenaService ocenaService;

    @RequestMapping(method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve ocene", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<OcenaDTO>> getAll(){
        return new ResponseEntity<>(ocenaService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca ocenu sa prosledjenim id-em", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OcenaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<OcenaDTO> findById(@PathVariable(value="id") Long id){
        return new ResponseEntity<>(ocenaService.findById(id),HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreira ocenu", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OcenaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<OcenaDTO> create(@RequestBody OcenaDTO ocenaDTO){
        return new ResponseEntity<>(ocenaService.create(ocenaDTO), HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update ocene", httpMethod = "PUT", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OcenaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<OcenaDTO> update(@RequestBody OcenaDTO ocenaDTO){
        return new ResponseEntity<>(ocenaService.update(ocenaDTO), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Brise ocenu", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
        ocenaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
