import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';
import { Aerodrom } from '../model';
import { Aviokompanija } from '../model';
import { Let } from '../model';
import { LetService } from '../letService/let.service';

@Component({
  selector: 'app-form-update-let',
  templateUrl: './form-update-let.component.html',
  styleUrls: ['./form-update-let.component.css'],
  providers: [AerodromSService, LetService]
})
export class FormUpdateLetComponent implements OnInit {
  @Input() letP:Let
  regFormA: FormGroup;
  submitted = false;
  aerodromi: Observable<Aerodrom[]>
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder ,
    private aerodromService: AerodromSService,
    private letService:LetService
  ) { }

  ngOnInit() {
    this.letP=new Let();
    this.aerodromi=this.aerodromService.getAllAerodromi();
    
    this.regFormA=this.formBuilder.group({
      id:[''],
      datumP: [''],
      datumS: [''],
      vremeP: [''],
      vremeS: [''],
      brojSedista: [''],
      vremePutovanja:[''],
      duzinaPutovanja:[''],
      aerodromP:[''],
      aerodromS:[''],
      aviokompanijaID: ['']
    })
    this.route.params.subscribe
      ( params =>  { const id = params['id'];
      if (id) {
        this.letService.getLet(id).subscribe(
          letN=> {this.letP=letN
          this.regFormA=this.formBuilder.group({
            id:[this.letP.id],
            datumP: [this.letP.datumP],
            datumS: [this.letP.datumS],
            vremeP: [this.letP.vremeP],
            vremeS: [this.letP.vremeS],
            brojSedista: [this.letP.brojSedista],
            vremePutovanja: [this.letP.vremePutovanja],
            duzinaPutovanja: [this.letP.duzinaPutovanja],
            aerodromP:[this.letP.aerodromP],
            aerodromS:[this.letP.aerodromS],
            aviokompanijaID: [this.letP.aviokompanijaID]
          })

        })
      }
     
    });
    
  }

  onSubmit(){
    this.submitted=true;
    //this.aerodrom.nazivAerodroma=this.regFormA.value.nazivAerodroma;
    this.regFormA.value.aerodromP=this.letP.aerodromP;
    this.regFormA.value.aerodromS=this.letP.aerodromS;
    this.letService.updateLet(this.regFormA.value);
  }

  onChange(value: any){
    console.log(value);
    this.letP.aerodromP.id=value;
  }

  onChange1(value: any){
    console.log(value);
    this.letP.aerodromS.id=value;
  }
}
