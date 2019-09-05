package com.controller.hotel;

import com.dto.hotel.HotelDTO;
import com.dto.hotel.SobaDTO;
import com.model.hotel.Hotel;
import com.model.hotel.Soba;
import com.service.hotel.HotelService;
import com.service.hotel.SobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value ="api/soba")
public class SobaController {

    @Autowired
    private SobaService sobaService;

    @Autowired
    private HotelService hotelService;

     @RequestMapping(value="/lista",method= RequestMethod.GET)
            	 public ResponseEntity<List<SobaDTO>> getAll(){

            	 List<Soba> soba = sobaService.findAll();
            	 List<SobaDTO> sobaDTO = new ArrayList<>();
            		for (Soba s : soba) {
            			sobaDTO.add(new SobaDTO(s));
            		}

            		return new ResponseEntity<List<SobaDTO>>(sobaDTO, HttpStatus.OK);
            }

            @RequestMapping(value = "/{id}", method = RequestMethod.GET)
            	public ResponseEntity<SobaDTO> getSoba(@PathVariable Long id) {

            		Optional<Soba> rentOptional = sobaService.findById(id);

            		if (rentOptional.isPresent()) {
            			return new ResponseEntity<>(new SobaDTO(rentOptional.get()), HttpStatus.OK);
            		} else {
            			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            		}

           	}

           	  @RequestMapping(method=RequestMethod.POST, consumes="application/json")
            public ResponseEntity<SobaDTO> saveSoba(@RequestBody SobaDTO sobaDTO){

                    Soba soba = new Soba();
                    soba.setSprat(sobaDTO.getSprat());

                    soba = sobaService.save(soba);
                    return new ResponseEntity<>(new SobaDTO(soba), HttpStatus.CREATED);
           }

            @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
            public ResponseEntity<Void> deleteSoba(@PathVariable Long id){

                    Optional<Soba> soba = sobaService.findById(id);

                    if (soba != null){
                            sobaService.remove(id);
                            return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
            }

            @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
            public ResponseEntity<SobaDTO> updateSoba(@RequestBody SobaDTO sobaDTO){

                    //a course must exist
                    Optional<Soba> soba = sobaService.findById(sobaDTO.getId());

                    if (soba == null) {
                            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }

                    soba.get().setSprat(sobaDTO.getSprat());

                    return new ResponseEntity<>(new SobaDTO(sobaService.save(soba.get())), HttpStatus.OK);
            }

          @RequestMapping(method = RequestMethod.POST,consumes = "application/json")
    	public ResponseEntity<HotelDTO> saveHotel(@RequestBody HotelDTO hotelDTO){

    		Hotel hotel = new Hotel();
    		hotel.setId(hotelDTO.getId());
    		hotel.setNaziv(hotelDTO.getNaziv());
    		hotel.setOpis(hotelDTO.getOpis());

    		hotel = hotelService.save(hotel);

    		return new ResponseEntity<>(new HotelDTO(hotel),HttpStatus.CREATED);

    	}

}
