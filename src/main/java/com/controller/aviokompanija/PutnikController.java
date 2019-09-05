package com.controller.aviokompanija;

import com.dto.aviokompanija.PutnikDTO;
import com.model.aviokompanija.Let;
import com.service.aviokompanija.PutnikService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/putnik")
public class PutnikController {
    @Autowired
    private PutnikService putnikService;


    @RequestMapping(value="/{id}", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreira putnika", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Let.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<PutnikDTO> create(@PathVariable Long id, @RequestBody PutnikDTO putnikDTO){
        return new ResponseEntity<>(putnikService.create(id, putnikDTO), HttpStatus.CREATED);
    }

}
