package com.controller.hotel;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.hotel.HotelDTO;
import com.model.hotel.Hotel;
import com.service.hotel.HotelService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value ="api/hotel")
        public class HotelController {


   @Autowired
      private HotelService hotelService;


         @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        	public ResponseEntity<HotelDTO> getHotel(@PathVariable Long id) {

        		Optional<Hotel> rentOptional = hotelService.findById(id);

        		if (rentOptional.isPresent()) {
        			return new ResponseEntity<>(new HotelDTO(rentOptional.get()), HttpStatus.OK);
        		} else {
        			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        		}

        	}

        @RequestMapping(method=RequestMethod.POST, consumes="application/json")
        public ResponseEntity<HotelDTO> saveHotel(@RequestBody HotelDTO hotelDTO){

                Hotel hotel = new Hotel();
                hotel.setNaziv(hotelDTO.getNaziv());

                hotel = hotelService.save(hotel);
                return new ResponseEntity<>(new HotelDTO(hotel), HttpStatus.CREATED);
        }

        @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
        public ResponseEntity<Void> deleteHotel(@PathVariable Long id){

                Optional<Hotel> hotel = hotelService.findById(id);

                if (hotel != null){
                        hotelService.remove(id);
                        return new ResponseEntity<>(HttpStatus.OK);
                } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
        public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotelDTO){

                //a course must exist
                Optional<Hotel> hotel = hotelService.findById(hotelDTO.getId());

                if (hotel == null) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                hotel.get().setNaziv(hotelDTO.getNaziv());

                return new ResponseEntity<>(new HotelDTO(hotelService.save(hotel.get())), HttpStatus.OK);
        }
}
