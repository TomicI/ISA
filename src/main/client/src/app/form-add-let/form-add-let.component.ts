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
    selector: 'app-form-add-let',
    templateUrl: './form-add-let.component.html',
    styleUrls: ['./form-add-let.component.css'],
    providers: [AerodromSService, LetService]
  })
  export class FormAddLetComponent implements OnInit {
    @Input() avikompanija:number
    regFormA: FormGroup;
    submitted = false;
    aerodromi: Observable<Aerodrom[]>;
    let: Let;
    pom: Let;
    pom1: any;
    constructor(
      private router: Router,
      private route: ActivatedRoute,
      private formBuilder: FormBuilder ,
      private aerodromService: AerodromSService,
      private letService:LetService
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
          this.let.aviokompanijaID=id;
        }});
      this.regFormA=this.formBuilder.group({
        datumP: [''],
        datumS: [''],
        vremeP: [''],
        vremeS: [''],
        brojSedista: [''],
        vremePutovanja: [''],
        duzinaPutovanja: [''],
        aerodromP:[''],
        aerodromS:[''],
        aviokompanijaID: [''],
        presedanje:[''],
        imaPresedanje:['false']
      })
    }

    onSubmit(){
      this.submitted=true;
      //this.aerodrom.nazivAerodroma=this.regFormA.value.nazivAerodroma;
      
      this.regFormA.value.aerodromP=this.let.aerodromP;
      this.regFormA.value.aerodromS=this.let.aerodromS;
      console.log("presedanje " + this.pom1.id);
      console.log(this.pom1);
      this.regFormA.value.presedanje=this.pom1.id;
      this.regFormA.value.aviokompanijaID=this.let.aviokompanijaID;
      
      this.pom1=this.letService.saveLet(this.regFormA.value);
      console.log(this.pom1);
      console.log("ovoooo " + this.pom1.__zone_symbol__value.presedanje);
    }

    onChange(value: any){
      console.log(value);
      this.let.aerodromP.id=value;
    }

    onChange1(value: any){
      console.log(value);
      this.let.aerodromS.id=value;
    }

    dodajP(){
      console.log("ima presedanje ");
      this.let.imaPresedanje=true;
      this.regFormA.value.imaPresedanje="true";
      this.onSubmit();
      
      this.router.navigateByUrl('letUpdate/'+(this.pom1.__zone_symbol__value.id-1));
      
    }
  }
