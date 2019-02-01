import { Component, OnInit, Input, Output ,EventEmitter} from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { RentacarService } from '../../services/rentacar.service';
import { Filijala } from 'src/app/model';



@Component({
  selector: 'app-form-fil',
  templateUrl: './form-fil.component.html',
  styleUrls: ['./form-fil.component.css'],
  providers: [RentacarService],
})
export class FormFilComponent implements OnInit {
  @Input() filijalaFormGroup: FormGroup;
  @Output() clicked = new EventEmitter<boolean>();

  filForm: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private rentaCarService: RentacarService) { }

  ngOnInit() {

    this.filForm = this.formBuilder.group({
      adresaFilInput: null
    });


  }
  onSubmit() {
    console.log('Kliknuo unutar forme!');
    this.clicked.emit(true);
  }

  nesto(){
    console.log('nesto');
    this.clicked.emit(true);
  }
  

}
