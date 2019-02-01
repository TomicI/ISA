import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { RentacarService } from '../services/rentacar.service';


@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.css'],
  providers: [RentacarService],
})
export class FormsComponent implements OnInit {

  regForm: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder ,
    private rentaCarService: RentacarService
  ) { }

  ngOnInit() {
    this.regForm = this.formBuilder.group({
      naziv: ['MILE'],
      opis: ['OPISA']
    });
  }


  get f() { return this.regForm.controls; }

  onSubmit() {
    this.submitted = true;

    //console.log(JSON.stringify(this.regForm.value));

    this.rentaCarService.saveRentACar(this.regForm.value);
  }

}
