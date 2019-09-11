import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';
import { DestinacijaComponent} from '../destinacija/destinacija.component';
import { Lokacija } from '../model';
import { Aerodrom } from '../model';
@Component({
  selector: 'app-form-add-aerodrom',
  templateUrl: './form-add-aerodrom.component.html',
  styleUrls: ['./form-add-aerodrom.component.css'],
  providers: [AerodromSService, DestinacijaComponent]
})
export class FormAddAerodromComponent implements OnInit {

  regFormA: FormGroup;
  submitted = false;
  aerodrom: Aerodrom;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder ,
    private aerodromService: AerodromSService,
    ) { }

  ngOnInit() {
    this.aerodrom=new Aerodrom();
    
    this.regFormA=this.formBuilder.group({
      naziv: [''],
      adresa:[''],
      grad: [''],
      drzava: ['']
    })
  }

  onSubmit(){
    this.submitted=true;
    this.aerodrom.naziv=this.regFormA.value.naziv;
    this.aerodrom.lokacijaDTO=new Lokacija();
    this.aerodrom.lokacijaDTO.adresa=this.regFormA.value.adresa;
    this.aerodrom.lokacijaDTO.grad=this.regFormA.value.grad;
    this.aerodrom.lokacijaDTO.drzava=this.regFormA.value.drzava;

    this.aerodromService.saveAerodrom(this.aerodrom).then(pom=>{
      console.log("vratilo");
      console.log(pom);
      this.router.navigateByUrl('aviokompanijaProfil');
    });
  }
}
