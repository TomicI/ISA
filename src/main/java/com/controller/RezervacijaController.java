package com.controller;

import com.dto.RezervacijaDTO;
import com.model.Rezervacija;
import com.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/rezervacija")
public class RezervacijaController {

    @Autowired
    private RezervacijaService as;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public ResponseEntity<List<RezervacijaDTO>> getAllUser(Principal user) {

        return new ResponseEntity<>(as.findAllUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllHistUser", method = RequestMethod.GET)
    public ResponseEntity<List<RezervacijaDTO>> getAllHistUser(Principal user) {

        return new ResponseEntity<>(as.findAllHistUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllInvitedUser", method = RequestMethod.GET)
    public ResponseEntity<List<RezervacijaDTO>> getAllInvitedUser(Principal user) {

        return new ResponseEntity<>(as.findAllInvited(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public ResponseEntity<List<RezervacijaDTO>> getAll() {

        List<Rezervacija> rezervacija = as.findAll();
        List<RezervacijaDTO> rezervacijaDTO = new ArrayList<>();
        for (Rezervacija s : rezervacija) {
            rezervacijaDTO.add(new RezervacijaDTO(s));
        }

        return new ResponseEntity<List<RezervacijaDTO>>(rezervacijaDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<RezervacijaDTO> getUserReservation(@PathVariable Long id,Principal user) {

        return new ResponseEntity<>(as.findUserReservation(id,user),HttpStatus.OK);

    }

    @RequestMapping(value = "/rez/{id}", method = RequestMethod.GET)
    public ResponseEntity<RezervacijaDTO> getReservation(@PathVariable Long id) {
        RezervacijaDTO r= as.getReservation(id);
        if(r.getKartaDTO()!=null)
            System.out.println("id karte " + r.getKartaDTO().getId());
        else
            System.out.println("karta je null");
        return new ResponseEntity<>(r,HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<RezervacijaDTO> saveRezervacija(@RequestBody RezervacijaDTO rezervacijaDTO) {

        Rezervacija rezervacija = new Rezervacija();
        //rezervacija.setDatumVremeP(rezervacijaDTO.getDatumVremeP());
        //rezervacija.setDatumVremeS(rezervacijaDTO.getDatumVremeS());

        rezervacija = as.save(rezervacija);
        return new ResponseEntity<>(new RezervacijaDTO(rezervacija), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<RezervacijaDTO> updateRezervacija(@RequestBody RezervacijaDTO rezervacijaDTO) {

        //a course must exist
        Optional<Rezervacija> rezervacija = as.findById(rezervacijaDTO.getId());

        if (rezervacija == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //rezervacija.get().setDatumVremeP(rezervacijaDTO.getDatumVremeP());
        //rezervacija.get().setDatumVremeS(rezervacijaDTO.getDatumVremeS());

        return new ResponseEntity<>(new RezervacijaDTO(as.save(rezervacija.get())), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRezervacija(@PathVariable Long id) {

        Optional<Rezervacija> rezervacija = as.findById(id);

        if (rezervacija != null) {
            as.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCancelSt(@PathVariable Long id) {
        return new ResponseEntity<>(as.cancelStatus(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/cancel",method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<?> cancelResevation(@RequestBody RezervacijaDTO rezervacijaDTO) {

        return new ResponseEntity<>(as.cancelConfirm(rezervacijaDTO.getId()), HttpStatus.OK);
    }

}
