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
  destinacije: Observable<Lokacija[]>;
  aerodrom: Aerodrom;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder ,
    private aerodromService: AerodromSService,
    private destinacija : Lokacija,
    ) { }

  ngOnInit() {
    this.aerodrom=new Aerodrom();
    //this.destinacije=this.destinacija.getDestinacije();
    console.log(this.destinacije);
    console.log('Uslo u save !');
    
    this.regFormA=this.formBuilder.group({
      nazivAerodroma: ['']
    })
  }

  onSubmit(){
    this.submitted=true;
    this.aerodrom.nazivAerodroma=this.regFormA.value.nazivAerodroma;
    this.aerodromService.saveAerodrom(this.aerodrom);
  }

  onChange(value: any){
    console.log(value);
    this.aerodrom.lokacija=value;
  }
}
