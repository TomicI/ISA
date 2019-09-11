package com.example.demo.unit.rentacar;

import com.dto.aviokompanija.AviokompanijaDTO;
import com.model.RentACar;
import com.model.aviokompanija.Aerodrom;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
        when(aviokompanijaRepository.findById(1L)).thenReturn(Optional.of(aviokompanija));
        Aviokompanija aviokompanija = aviokompanijaService.findOne(1L);
        assertEquals(aviokompanija.getNaziv(),"Aeroporto De Lisboa");


    }

    @Test
    public void when_save_aviokompanija_it_should_return_aviokompanija(){
        aviokompanija = new Aviokompanija(1L,"Aeroporto De Lisboa","Aviokompanija");
        when(aviokompanijaRepository.save(aviokompanija)).thenReturn(new Aviokompanija());
        AviokompanijaDTO created=aviokompanijaService.create(new AviokompanijaDTO(aviokompanija));
        assertEquals(created.getNaziv(),(aviokompanija.getNaziv()));

    }


}
