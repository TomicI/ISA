package com.example.demo.unit.rentacar;




import com.model.RentACar;
import com.repository.RentACarRepository;
import com.service.RentACarService;
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
public class RentACarServiceTest {

    @Mock
    RentACarRepository rentACarRepository;

    @InjectMocks
    RentACarService rentACarService;

    @Mock
    RentACar rentACar;


    @Test
    public void getBy(){
        rentACar = new RentACar(1L,"Hertz","Rent A Car");
        Mockito.when(rentACarRepository.findById(1L)).thenReturn(Optional.of(rentACar));
        RentACar rentACar = rentACarService.getOne(1L);
        assertEquals(rentACar.getNaziv(),"Hertz");


    }






}
