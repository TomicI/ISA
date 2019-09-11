package com.example.demo.unit.rentacar;

import com.model.RentACar;
import com.model.aviokompanija.Aviokompanija;
import com.repository.RentACarRepository;
import com.repository.aviokompanija.AviokompanijaRepository;
import com.service.RentACarService;
import com.service.aviokompanija.AviokompanijaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AviokompanijaServiceTest {
    @Mock
    AviokompanijaRepository aviokompanijaRepository;

    @InjectMocks
    AviokompanijaService aviokompanijaService;

    @Mock
    Aviokompanija aviokompanija;

    @Test
    public void getBy(){
        aviokompanija = new Aviokompanija(1L,"Aeroporto De Lisboa","Aviokompanija");
        Mockito.when(aviokompanijaRepository.findById(1L)).thenReturn(Optional.of(aviokompanija));
        Aviokompanija aviokompanija = aviokompanijaService.findOne(1L);
        assertEquals(aviokompanija.getNaziv(),"Aeroporto De Lisboa");


    }



}
