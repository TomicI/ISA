import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { CenovnikService } from 'src/app/services/cenovnik.service';

import {NgbDateParserFormatter, NgbCalendar, NgbDate, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import { NgbDateAdapter, NgbDateNativeAdapter } from '@ng-bootstrap/ng-bootstrap';
import {Vozilo} from "../../model";


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

  minDatum : NgbDate;

  minimumDate: Date;

  odDate:Date;
  doDate:Date;

  minDateStruct:NgbDateStruct;

  constructor(private formBuilder: FormBuilder, private cenService: CenovnikService,
    private ngbDate: NgbDateParserFormatter,private calendar: NgbCalendar) { }


  ngOnInit() {
    this.minDatum = this.calendar.getToday();


    let v : Vozilo = this.cenovnikFormGroup.get('CenovnikRent').get('voziloDTO').value;

    console.log(v);



    this.cenService.getDate(v.id).subscribe(data=>{
      console.log(data);

      this.minimumDate = data;

      let tempDate = new Date(data);

      //tempDate.setHours(tempDate.getHours()+2);

      console.log(tempDate.getMonth());
      console.log(tempDate.getDate());
      console.log(tempDate.getDay());

      this.minDatum = new NgbDate(tempDate.getFullYear(),tempDate.getMonth()+1,tempDate.getDate());


      console.log(this.minDatum);

    });
  }


  async submit() {

    this.odDate = this.cenovnikFormGroup.get('CenovnikRent').get('odDatuma').value;
    this.odDate.setHours(0,0,0);

    this.doDate = this.cenovnikFormGroup.get('CenovnikRent').get('doDatuma').value;
    this.doDate.setHours(0,0,0);


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
