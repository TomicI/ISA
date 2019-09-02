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
  providers: [AerodromSService, DestinacijaComponent]
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
    private aerodromService: AerodromSService,
    private destinacija : Lokacija) { }


  ngOnInit() {
    this.aerodrom=new Aerodrom();
    //this.destinacije=this.destinacija.getDestinacije();
    this.regFormA=this.formBuilder.group({
      naziv: ['']
    })

    this.route.params.subscribe
      ( params =>  { const id = params['id'];
      if (id) {
        console.log(`aerodrom with id '${id}' `);
        this.aerodromService.getAerodrom(id).then(aerodrom=>{
          console.log(`aerodrom with name `+ aerodrom.naziv);
          if (aerodrom) {
            this.aerodrom=aerodrom;
            this.regFormA=this.formBuilder.group({
              nazivAerodroma: aerodrom.naziv,

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
    this.aerodromService.updateAerodrom(this.aerodrom);
  }

  onChange(value: any){
    console.log(value);
    this.aerodrom.lokacija.id=value;
  }
}
