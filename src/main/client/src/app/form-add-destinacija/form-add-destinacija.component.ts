import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

import { Router } from '@angular/router';
import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';

@Component({
  selector: 'app-form-add-destinacija',
  templateUrl: './form-add-destinacija.component.html',
  styleUrls: ['./form-add-destinacija.component.css'],
  providers: [AerodromSService]
})
export class FormAddDestinacijaComponent implements OnInit {
  regFormD: FormGroup;
  submitted = false;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder ,
    private aerodromService: AerodromSService) { }

  ngOnInit() {
    console.log('Uslo u save !')
    this.regFormD=this.formBuilder.group({
      nazivDestinacije: ['']
    })
  }

  onSubmit(){
    this.submitted=true;
    this.aerodromService.saveDestinacija(this.regFormD.value);
  }

}
