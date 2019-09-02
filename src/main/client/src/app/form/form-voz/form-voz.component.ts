import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { RentacarService } from '../../services/rentacar.service';
import { Vozilo } from '../../model';
import { VehicleService } from 'src/app/services/vehicle.service';



@Component({
  selector: 'app-form-voz',
  templateUrl: './form-voz.component.html',
  styleUrls: ['./form-voz.component.css'],
})
export class FormVozComponent implements OnInit {
  @Input() voziloFormGroup: FormGroup;
  @Input() edit: boolean;
  @Output() clickedVeh = new EventEmitter<boolean>();

  regVoz: FormGroup;
  submitted = false;

  error=false;

  message:any;

  constructor(
    private formBuilder: FormBuilder,
    private rentaCarService: RentacarService,
    private vehicleService: VehicleService
  ) { }

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

  async submit() {
    console.log(JSON.stringify(this.voziloFormGroup.get('vozilo').value));

    if (this.edit === true) {
      console.log('Edit VEH');
      await this.vehicleService.updateVehicle(this.voziloFormGroup.get('vozilo').value).then(value => {
        this.clickedVeh.emit(true);
      }).catch(error=>{
          this.message = error.error.message;
          this.error = true;
      });
    } else {
      await this.vehicleService.saveVehicle(this.voziloFormGroup.get('vozilo').value).then(value => {
        this.clickedVeh.emit(true);
      });

    }


  }

  async delete() {
    const pom = this.voziloFormGroup.get('vozilo').get('id').value;
    if (this.edit === true) {
      await this.vehicleService.removeVehicle(pom).then(value => {
        this.clickedVeh.emit(true);
      }).catch(error=>{
        this.message = error.error.message;
        this.error = true;
      });
    }

  }

}
