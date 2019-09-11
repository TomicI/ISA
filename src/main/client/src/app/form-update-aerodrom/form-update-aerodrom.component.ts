import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';
import {Aerodrom} from '../model';
import { ActivatedRoute, Router } from '@angular/router';
import { DestinacijaComponent} from '../destinacija/destinacija.component';
import { Lokacija } from '../model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-form-update-aerodrom',
  templateUrl: './form-update-aerodrom.component.html',
  styleUrls: ['./form-update-aerodrom.component.css'],
  providers: [AerodromSService]
})
export class FormUpdateAerodromComponent implements OnInit {
  @Input() aerodrom:Aerodrom;
  regFormA: FormGroup;
  destinacije: Observable<Lokacija[]>;
  submitted = false;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder ,
    private route: ActivatedRoute,
    private aerodromService: AerodromSService) { }


  ngOnInit() {
    this.aerodrom=new Aerodrom();
    //this.destinacije=this.destinacija.getDestinacije();
    this.regFormA=this.formBuilder.group({
      naziv: [''],
      adresa:[''],
      grad: [''],
      drzava: ['']
    })

    this.route.params.subscribe
      ( params =>  { const id = params['id'];
      if (id) {
        this.aerodromService.getAerodrom(id).then(aerodrom=>{
          if (aerodrom) {
            this.aerodrom=aerodrom;
            this.regFormA=this.formBuilder.group({
              naziv: aerodrom.naziv,
              adresa: aerodrom.lokacijaDTO.adresa,
              grad: aerodrom.lokacijaDTO.grad,
              drzava: aerodrom.lokacijaDTO.drzava
            })

          } else {
            console.log(`aerodrom with id '${id}' not found `);
          }})



      }
    });
  }

  onSubmit(){
    this.submitted=true;
    this.aerodrom.naziv=this.regFormA.value.naziv;
    this.aerodrom.lokacijaDTO=new Lokacija();
    this.aerodrom.lokacijaDTO.adresa=this.regFormA.value.adresa;
    this.aerodrom.lokacijaDTO.grad=this.regFormA.value.grad;
    this.aerodrom.lokacijaDTO.drzava=this.regFormA.value.drzava;

    this.aerodromService.updateAerodrom(this.aerodrom).then(pom=>
    {
      console.log("vratilo");
      console.log(pom);
      this.router.navigateByUrl('aviokompanijaProfil');
    });
  }

}
