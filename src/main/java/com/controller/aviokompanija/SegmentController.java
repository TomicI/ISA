package com.controller.aviokompanija;

import com.dto.aviokompanija.SedisteDTO;
import com.dto.aviokompanija.SegmentDTO;
import com.service.aviokompanija.SegmentService;
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
@RequestMapping(value="api/segment")
public class SegmentController {

    @Autowired
    private SegmentService segmentService;

    @RequestMapping(method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve segmente", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<SegmentDTO>> getAll(){
        List<SegmentDTO> segmenti=segmentService.getAll();
        
        return new ResponseEntity<>(segmenti, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca segmet sa prosledjenim id-em", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SegmentDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<SegmentDTO> findById(@PathVariable(value="id") Long id){
        return new ResponseEntity<>(segmentService.findById(id),HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreira segment", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SegmentDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<SegmentDTO> create(@PathVariable(value="id") Long id, @RequestBody SegmentDTO segmentDTO){
        return new ResponseEntity<>(segmentService.create(id, segmentDTO), HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update segmenta", httpMethod = "PUT", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SegmentDTO.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<SegmentDTO> update(@RequestBody SegmentDTO segmentDTO){
        return new ResponseEntity<>(segmentService.update(segmentDTO), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Brise segment", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
        segmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/sedista/{idL}",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sva sedista", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<List<SedisteDTO>> segmenti(@PathVariable(value = "id") Long id, @PathVariable(value = "idL") Long idL){
        return new ResponseEntity<>(segmentService.getSedista(id, idL), HttpStatus.OK);
    }
}
