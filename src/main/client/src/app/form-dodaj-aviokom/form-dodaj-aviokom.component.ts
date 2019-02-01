import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AviokompanijaService } from '../aviokompanija/aviokompanija.service';

import { Router } from '@angular/router';

@Component({
  selector: 'app-form-dodaj-aviokom',
  templateUrl: './form-dodaj-aviokom.component.html',
  styleUrls: ['./form-dodaj-aviokom.component.css'],
  providers: [AviokompanijaService],

})
export class FormDodajAviokomComponent implements OnInit {

  regForm: FormGroup;
  submitted = false;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder ,
    private aviokompanijaService: AviokompanijaService
  ) { }

  ngOnInit() {
    console.log('Uslo u save !')
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
    this.aviokompanijaService.saveAviokompanija(this.regForm.value);
    this.router.navigateByUrl('aviokom-list');
  }
}
