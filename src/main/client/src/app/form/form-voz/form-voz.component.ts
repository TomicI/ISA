import { Component, OnInit , Input} from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import {RentacarService } from '../../services/rentacar.service';
import { Vozilo } from '../../model';


@Component({
  selector: 'app-form-voz',
  templateUrl: './form-voz.component.html',
  styleUrls: ['./form-voz.component.css'],
})
export class FormVozComponent implements OnInit {
  @Input() vozilo: Vozilo;

  regVoz: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder ,
    private rentaCarService: RentacarService
    ) {}

  ngOnInit() {
    this.regVoz = this.formBuilder.group({
      nazivVozInput: null,
      markaVozInput: null,
      modelVozInput: null,
      vrataVozSelect: null,
      sedVozSelect: null,
      torVozSelect: null,
      gorivoVozSelect: null,
      rezVozInput: null,
      potVozInput: null,
      klimaVozSelect: null,
      dodVozText: null
    });

  }

}
