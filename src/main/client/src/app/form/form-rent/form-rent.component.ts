import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
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
  @Input() rentFormGroup: FormGroup;
  @Input() edit: boolean;
  @Output() clicked = new EventEmitter<boolean>();
  @Input() rentACar: RentACar;

  error = '';

  submitted = false;

  submitError = false;

  constructor(
    private formBuilder: FormBuilder,
    private rentaCarService: RentacarService) { }

  ngOnInit() {
    this.submitError = false;

  }

  async submit() {

    console.log(this.rentFormGroup.value);

    await this.rentaCarService.updateRent(this.rentFormGroup.value).then( data => {
       this.clicked.emit(true);
    }).catch(error => {
      this.error = error.error.message;
      this.submitError = true;
    });
  }

}
