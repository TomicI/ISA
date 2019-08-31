import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AviokompanijaService } from '../aviokompanija/aviokompanija.service';

import { Router } from '@angular/router';
import {Aviokompanija, User} from "../model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-form-dodaj-aviokom',
  templateUrl: './form-dodaj-aviokom.component.html',
  styleUrls: ['./form-dodaj-aviokom.component.css'],
  providers: [AviokompanijaService],

})
export class FormDodajAviokomComponent implements OnInit {

  regForm: FormGroup;
  submitted = false;
  aviokompanija: Aviokompanija;


  constructor(
    private router: Router,
    private formBuilder: FormBuilder ,
    private aviokompanijaService: AviokompanijaService,
    private userService: UserService
  ) { }

  ngOnInit() {
    console.log('Uslo u save !');


    this.regForm=this.formBuilder.group({
      naziv: [''],
      adresa: [''],
      opis: [''],
      prosecnaOcena:['']
    })
  }

  get f(){return this.regForm.controls;}

  onSubmit(){
    this.submitted=true;
    this.aviokompanijaService.saveAviokompanija(this.regForm.value).then(pom=>
      {
        this.router.navigateByUrl('aviokom-list/');
      }
    )
  }
}
