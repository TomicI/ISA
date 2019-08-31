package com.controller.aviokompanija;

import com.dto.aviokompanija.KonfiguracijaLetaDTO;
import com.dto.aviokompanija.SegmentDTO;
import com.service.aviokompanija.KonfiguracijaLetaService;
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
@RequestMapping(value="api/konfiguracijaLeta")
public class KonfiguracijaLetaController {

    @Autowired
    private KonfiguracijaLetaService konfiguracijaLetaService;

    @RequestMapping(method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve konfiguracije letova", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<KonfiguracijaLetaDTO>> getAll(){
        return new ResponseEntity<>(konfiguracijaLetaService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca konfiguraciju leta sa prosledjenim id-em", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = KonfiguracijaLetaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<KonfiguracijaLetaDTO> findById(@PathVariable(value="id") Long id){
        return new ResponseEntity<>(konfiguracijaLetaService.findById(id),HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreira kategoriju sedista", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = KonfiguracijaLetaDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<KonfiguracijaLetaDTO> create(@RequestBody KonfiguracijaLetaDTO konfiguracijaLetaDTO){
        return new ResponseEntity<>(konfiguracijaLetaService.create(konfiguracijaLetaDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Brise konfiguraciju sedista", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
        konfiguracijaLetaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/segment",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreira segment za kategoriju sedista", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SegmentDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<SegmentDTO> createSegment(@PathVariable(value = "id") Long id, @RequestBody SegmentDTO segmentDTO){
        return new ResponseEntity<>(konfiguracijaLetaService.createSegment(id, segmentDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/segmenti",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve konfiguracije letova", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<SegmentDTO>> segmenti(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(konfiguracijaLetaService.segmenti(id), HttpStatus.OK);
    }

}
