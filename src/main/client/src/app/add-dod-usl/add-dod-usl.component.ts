import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AerodromSService} from "../aerodrom-s/aerodrom-s.service";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";

@Component({
  selector: 'app-add-dod-usl',
  templateUrl: './add-dod-usl.component.html',
  styleUrls: ['./add-dod-usl.component.css'],
  providers: [AviokompanijaService]
})
export class AddDodUslComponent implements OnInit {
  regFormA: FormGroup;
  constructor( private router: Router,
               private formBuilder: FormBuilder ,
               private aviokompanijaService: AviokompanijaService,) { }

  ngOnInit() {
    this.regFormA=this.formBuilder.group({
      naziv: [''],
      opis:[''],
      cena: ['']
    })
  }

  onSubmit(){
    this.aviokompanijaService.createDodUsl(this.regFormA.value).then(pom=>
    {
      console.log("vratilo");
      console.log(pom);
      this.router.navigateByUrl('aviokompanijaProfil');
    })
  }
}
