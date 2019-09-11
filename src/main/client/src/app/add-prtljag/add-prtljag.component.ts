import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AerodromSService} from "../aerodrom-s/aerodrom-s.service";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";

@Component({
  selector: 'app-add-prtljag',
  templateUrl: './add-prtljag.component.html',
  styleUrls: ['./add-prtljag.component.css'],
  providers:[AviokompanijaService]
})
export class AddPrtljagComponent implements OnInit {
  regFormA: FormGroup;
  constructor( private router: Router,
               private formBuilder: FormBuilder ,
               private aviokompanijaService: AviokompanijaService,) { }

  ngOnInit() {
    this.regFormA=this.formBuilder.group({
      duzina: [''],
      sirina:[''],
      tezina: [''],
      cena: ['']
    })
  }

  onSubmit(){
   this.aviokompanijaService.createPrtljag(this.regFormA.value).then(pom=>
   {
     console.log("vratilo");
     console.log(pom);
     this.router.navigateByUrl('aviokompanijaProfil');
   })
  }
}
