package com.controller.hotel;


import com.dto.hotel.CenovnikHotelDTO;
import com.model.CenovnikRentACar;
import com.model.hotel.CenovnikHotel;
import com.model.hotel.Soba;
import com.service.hotel.CenovnikHotelService;
import com.service.hotel.SobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/cenovnikHotel")
public class CenovnikHotelController {

    @Autowired
    private CenovnikHotelService cenovnikHotelService;

    @Autowired
    private SobaService sobaService;

    //


}
