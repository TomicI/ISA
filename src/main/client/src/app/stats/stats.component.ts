import { Component, OnInit } from '@angular/core';
import {
  NgbCalendar,
  NgbDate,
  NgbDateAdapter,
  NgbDateNativeAdapter,
  NgbDateParserFormatter
} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FilijalaService} from "../services/filijala.service";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css'],
  providers:[FilijalaService]
})
export class StatsComponent implements OnInit {

  constructor(private calendar: NgbCalendar,
              private formBuilder: FormBuilder,
              private ngbDate: NgbDateParserFormatter,
              private filijalaService:FilijalaService) { }


  searchFormGroup: FormGroup;

  fromDate:NgbDate;
  toDate: NgbDate;

  from:Date;
  to:Date;

  option:number;

  params:any;

  ngOnInit() {

    this.option = 0;

    this.fromDate = this.calendar.getToday();
    this.toDate = this.calendar.getNext(this.fromDate, 'd', 1)

    this.searchFormGroup = this.formBuilder.group({
      pickDate: this.fromDate,
      dropDate: this.toDate,
      income:0,
      number:0,
    });

  }

  onDateSelected(){

    this.toDate = this.calendar.getNext(this.searchFormGroup.get('pickDate').value, 'd', 1);

    this.searchFormGroup.setValue({
      pickDate: this.searchFormGroup.get('pickDate').value,
      dropDate: this.calendar.getNext(this.searchFormGroup.get('pickDate').value, 'd', 1),
      income:0,
      number:0
    });

  }

  onSubmit(){

    const pickDate = this.searchFormGroup.get('pickDate').value;
    const dropDate = this.searchFormGroup.get('dropDate').value;

    console.log(this.ngbDate.format(pickDate));

    const dateP = new Date(pickDate.year, pickDate.month - 1, pickDate.day, 0, 0, 0).toLocaleString();
    const dateD = new Date(dropDate.year, dropDate.month - 1, dropDate.day, 0, 0, 0).toLocaleString();

    this.params = {

      from:dateP,
      to:dateD
    };


    this.filijalaService.getIncome(this.params).subscribe(data=> {
      this.searchFormGroup.patchValue({income:data});
    });

  }


  onSubmitNumber(){

    const pickDate = this.searchFormGroup.get('pickDate').value;

    const dateP = new Date(pickDate.year, pickDate.month - 1, pickDate.day, 0, 0, 0).toLocaleString();

    this.params = {

      pick:dateP,
      opt:this.option
    };

    this.filijalaService.getDaily(this.params).subscribe(data=> {
      this.searchFormGroup.patchValue({income:data});
    });


  }

}
