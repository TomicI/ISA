  import { Component, OnInit, Input } from '@angular/core';
  import { FormGroup, FormBuilder } from '@angular/forms';
  import { Observable } from 'rxjs';
  import { Router, ActivatedRoute } from '@angular/router';
  import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';
  import { Aerodrom } from '../model';
  import { Aviokompanija } from '../model';
  import { Let } from '../model';
  import { LetService } from '../letService/let.service';
  import {AviokompanijaService } from '../aviokompanija/aviokompanija.service'

  @Component({
    selector: 'app-form-add-let',
    templateUrl: './form-add-let.component.html',
    styleUrls: ['./form-add-let.component.css'],
    providers: [AerodromSService, LetService, AviokompanijaService]
  })
  export class FormAddLetComponent implements OnInit {
    @Input() avikompanija:number
    regFormA: FormGroup;
    submitted = false;
    aerodromi: Observable<Aerodrom[]>;
    let: Let;
    pom: Let;
    pom1: any;
    checkedList: Aerodrom[]=[];
    constructor(
      private router: Router,
      private route: ActivatedRoute,
      private formBuilder: FormBuilder ,
      private aerodromService: AerodromSService,
      private letService:LetService,
      private aviokompanijaSerivce: AviokompanijaService
    ) { }

    ngOnInit() {
      this.pom1=new Let();
      this.let=new Let();
      this.let.aerodromP=new Aerodrom();
      this.let.aerodromS=new Aerodrom();
      this.let.aerodromP.id=-1;
      this.let.aerodromS.id=-1;
      this.aerodromi=this.aerodromService.getAllAerodromi();

      this.route.params.subscribe
        ( params =>  { const id = params['id'];
        if (id) {
          this.aviokompanijaSerivce.getAviokompanija(id).subscribe(aviok=> this.let.aviokompanijaID=aviok);
          
        }});
      this.regFormA=this.formBuilder.group({
        datumP: [''],
        datumS: [''],
        vremeP: [''],
        vremeS: [''],
        vremePutovanja: [''],
        duzinaPutovanja: [''],
        aerodromP:[''],
        aerodromS:[''],
        aviokompanijaID: [''],
        presedanje:[''],
        brojSegmenata:[''],
        brojKolona: [''],
        brojRedova:[''],
        opis:['']
      })
    }

    onSubmit(){
      this.submitted=true;
      //this.aerodrom.nazivAerodroma=this.regFormA.value.nazivAerodroma;
      
      this.regFormA.value.aerodromP=this.let.aerodromP;
      this.regFormA.value.aerodromS=this.let.aerodromS;
      this.regFormA.value.presedanje=this.checkedList;
      this.regFormA.value.aviokompanijaID=this.let.aviokompanijaID;
      
      this.pom1=this.letService.saveLet(this.regFormA.value);
    }

    onChange(value: any){
      console.log(value);
      this.let.aerodromP.id=value;
    }

    onChange1(value: any){
      console.log(value);
      this.let.aerodromS.id=value;
    }

    

    onCheckboxChange(option, event) {
      if(event.target.checked) {
        this.checkedList.push(option);
      } else {
        for(var i=0 ; i < this.checkedList.length; i++) {
          if(this.checkedList[i].id == option.id){
            this.checkedList.splice(i,1);
          }
        }
      }
      console.log(this.checkedList);
    }
}
