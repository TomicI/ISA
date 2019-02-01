import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { RentacarService } from '../../services/rentacar.service';
import { RentACar } from '../../model';

@Component({
  selector: 'app-form-rent',
  templateUrl: './form-rent.component.html',
  styleUrls: ['./form-rent.component.css'],
  providers: [RentacarService],
})
export class FormRentComponent implements OnInit {
  @Input() rentACar: RentACar;

  rntForm: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private rentaCarService: RentacarService) { }

  ngOnInit() {
    this.rntForm = this.formBuilder.group({
      nazivRNCInput: null,
      opisRNC: null
    });

  }
}
