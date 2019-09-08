import { Component, OnInit, Input, Output ,EventEmitter} from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { RentacarService } from '../../services/rentacar.service';
import { Filijala } from 'src/app/model';
import { FilijalaService } from 'src/app/services/filijala.service';



@Component({
  selector: 'app-form-fil',
  templateUrl: './form-fil.component.html',
  styleUrls: ['./form-fil.component.css'],
  providers: [RentacarService],
})
export class FormFilComponent implements OnInit {
  @Input() filijalaFormGroup: FormGroup;
  @Input() edit: boolean;
  @Output() clicked = new EventEmitter<boolean>();

  filForm: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private rentaCarService: RentacarService,
    private filijalaService: FilijalaService) { }

  ngOnInit() {

    console.log(this.filijalaFormGroup.value);


  }
  async submit() {
    console.log(JSON.stringify(this.filijalaFormGroup.value));
    if (this.edit === true) {
      console.log('Edit mode');

     await this.filijalaService.updateFilijala(this.filijalaFormGroup.value);
 
    } else {
     await this.filijalaService.saveFilijala(this.filijalaFormGroup.value);
    }

    console.log('Kliknuo unutar forme!');
    this.clicked.emit(true);
  }



  async delete() {
    const pom = this.filijalaFormGroup.get('id').value;
    if (this.edit === true) {
      console.log('Edit mode');

      await this.filijalaService.removeFilijala(pom);
      this.clicked.emit(true);
    }

  }

}
