import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { CenovnikService } from 'src/app/services/cenovnik.service';

import { NgbDateParserFormatter, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateAdapter, NgbDateStruct, NgbDateNativeAdapter } from '@ng-bootstrap/ng-bootstrap';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';


@Component({
  selector: 'app-form-cen',
  templateUrl: './form-cen.component.html',
  styleUrls: ['./form-cen.component.css'],
  providers: [{ provide: NgbDateAdapter, useClass: NgbDateNativeAdapter }]
})
export class FormCenComponent implements OnInit {

  @Input() cenovnikFormGroup: FormGroup;
  @Input() edit: boolean;
  @Output() clickedCen = new EventEmitter<boolean>();

  datenow = new Date();

  minDatum = new Date();

  constructor(private formBuilder: FormBuilder, private cenService: CenovnikService,
    private ngbDate: NgbDateParserFormatter) { }


  ngOnInit() {
  }

  async submit() {
    console.log(this.cenovnikFormGroup.get('CenovnikRent').value);

    if (this.edit === true) {
      await this.cenService.updateCenovnik(this.cenovnikFormGroup.get('CenovnikRent').value);
    } else {
      await this.cenService.saveCenovnik(this.cenovnikFormGroup.get('CenovnikRent').value);
    }

    this.clickedCen.emit(true);
  }

  async delete() {
    console.log(this.cenovnikFormGroup.get('CenovnikRent').get('id').value);
    if (this.edit === true) {
      await this.cenService.removeCenovnik(this.cenovnikFormGroup.get('CenovnikRent').get('id').value);
    }
    this.clickedCen.emit(true);

  }

}
