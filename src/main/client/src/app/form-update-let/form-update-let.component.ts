import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';
import { Aerodrom } from '../model';
import { Aviokompanija } from '../model';
import { Let } from '../model';
import { LetService } from '../letService/let.service';
import { forEach } from '@angular/router/src/utils/collection';

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
  aerodromi: Observable<Aerodrom[]>;
  checkedList: Aerodrom[]=[];
  checkedList1: Aerodrom[]=[];
  lista: Aerodrom[]=[];
  lista1: Aerodrom[]=[];
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
      aviokompanijaID: [''],
      presedanje:[''],
      brojSegmenata:[''],
      brojKolona:[''],
      brojRedova:[''],
      opis:['']
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
            vremePutovanja: [this.letP.vremePutovanja],
            duzinaPutovanja: [this.letP.duzinaPutovanja],
            aerodromP:[this.letP.aerodromP],
            aerodromS:[this.letP.aerodromS],
            aviokompanijaID: [this.letP.aviokompanijaID],
            presedanje:[''],
            brojSegmenata:[this.letP.brojSegmenata],
            brojKolona:[this.letP.brojKolona],
            brojRedova:[this.letP.brojRedova],
            opis:[this.letP.opis]
          })
          this.checkedList1=this.letP.presedanje;
          for(var i=0; i<this.checkedList1.length; i++){
            this.checkedList.push(this.checkedList1[i])
          }
          this.pomF();
        })
      }
     
    });
  }

  onSubmit(){
    this.submitted=true;
    //this.aerodrom.nazivAerodroma=this.regFormA.value.nazivAerodroma;
    this.regFormA.value.aerodromP=this.letP.aerodromP;
    this.regFormA.value.aerodromS=this.letP.aerodromS;
    
    for(var i=0; i<this.lista1.length; i++){
      this.checkedList.push(this.lista1[i]);
    }
    this.regFormA.value.presedanje=this.checkedList;
    this.letService.updateLet(this.regFormA.value);
  }

  /*onChange(value: any){
    console.log(value);
    this.letP.aerodromP.id=value;
  }

  onChange1(value: any){
    console.log(value);
    this.letP.aerodromS.id=value;
  }*/

  onCheckboxChange(option, event) {
    
    if(event.target.checked) {
      this.checkedList.push(option);
    } else {
      for(var i=0 ; i < this.checkedList.length; i++) {
        if(this.checkedList[i].id == option.id){
          this.checkedList.splice(i,1);
          console.log("lista l " + this.lista.length);
          console.log("chL l " + this.checkedList1.length);
        }
      }
    }
    console.log(this.checkedList);
  }

  onCheckboxChange1(option, event) {
    if(event.target.checked) {
      this.lista1.push(option);
      console.log(option);
    } else {
      for(var i=0 ; i < this.lista1.length; i++) {
        if(this.lista1[i].id == option.id){
          this.lista1.splice(i,1);
        }
      }
    }
    console.log(this.lista1);
  }

  pomF(){
    var ima=true;
    this.aerodromi.forEach((a: Aerodrom[]) => {
      for(var j=0;j<a.length;j++){
          console.log("uslo ovde");
          for(var i=0 ; i < this.checkedList1.length; i++) {
            if(this.checkedList1[i].id ==a[j].id){
              console.log("ima " + a[j].nazivAerodroma);
              ima=false;
            }
          }
          if(ima){
            console.log("ubaceno " + a[j].nazivAerodroma);
            this.lista.push(a[j]);
          }
          ima=true;
      }
      
    })
  }

}
