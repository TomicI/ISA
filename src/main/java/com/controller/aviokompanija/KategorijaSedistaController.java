package com.controller.aviokompanija;

import com.dto.aviokompanija.KategorijaSedistaDTO;
import com.service.aviokompanija.KategorijaSedistaService;
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
@RequestMapping(value="api/kategorijaSedista")
public class KategorijaSedistaController {

    @Autowired
    private KategorijaSedistaService kategorijaSedistaService;

    @RequestMapping(method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve kategorije sedista", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<KategorijaSedistaDTO>> getAll(){
        return new ResponseEntity<>(kategorijaSedistaService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca kategoriju sedista sa prosledjenim id-em", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = KategorijaSedistaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<KategorijaSedistaDTO> findById(@PathVariable(value="id") Long id){
        return new ResponseEntity<>(kategorijaSedistaService.findById(id),HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreira kategoriju sedista", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = KategorijaSedistaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<KategorijaSedistaDTO> create(@RequestBody KategorijaSedistaDTO kategorijaSedistaDTO, Principal user){
        return new ResponseEntity<>(kategorijaSedistaService.create(kategorijaSedistaDTO, user.getName()), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Brise kategoriju sedista", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
        kategorijaSedistaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
