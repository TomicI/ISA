import { Component, OnInit, Input, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { RentacarService } from '../services/rentacar.service';
import { RentACar, Filijala } from '../model';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';
import { NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder } from '@angular/forms';
import { PARAMETERS } from '@angular/core/src/util/decorators';

@Component({
  selector: 'app-rentacar-list',
  templateUrl: './rentacar-list.component.html',
  styleUrls: ['./rentacar-list.component.css'],
  providers: [RentacarService],

})
export class RentacarListComponent implements OnInit {

  searchFormGroup: FormGroup;

  params: any = {};

  minDatum = this.calendar.getToday();

  arrayOfHours: string[] = ['00:00', '00:30', '01:00', '01:30', '02:00', '02:30', '03:00', '03:30', '04:00', '04:30', '05:00', '05:30',
    '06:00', '06:30', '07:00', '07:30', '08:00', '08:30', '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00', '12:30', '13:00'
    , '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00', '19:30'
    , '20:00', '20:30', '21:00', '21:30', '22:00', '22:30', '23:00', '23:30'];

  fromDate: NgbDate;
  toDate: NgbDate;

  rentACars: Observable<RentACar[]>;
  filijala: Observable<Object>;

  constructor(
    private rentACarService: RentacarService,
    private route: ActivatedRoute,
    private calendar: NgbCalendar,
    private router: Router,
    private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.route.queryParams.subscribe(params => {
      console.log(params);
    });

    this.getRentACars();

    this.fromDate = this.calendar.getToday();
    this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 1);

    this.searchFormGroup = this.formBuilder.group({
      content: '',
      pickDate: this.fromDate,
      dropDate: this.toDate,
      pickTime: '12:00',
      dropTime: '12:00'
    });
  }

  getRentACars() {
    this.rentACars = this.rentACarService.getAll();
  }

  getFilijala(id: number) {
    this.filijala = this.rentACarService.getAllFilijale(id);
  }

  onDateSelected() {


    this.toDate = this.calendar.getNext(this.searchFormGroup.get('pickDate').value, 'd', 1);
    this.searchFormGroup.setValue({
      content: this.searchFormGroup.get('content').value,
      pickDate: this.searchFormGroup.get('pickDate').value,
      dropDate: this.calendar.getNext(this.searchFormGroup.get('pickDate').value, 'd', 1),
      pickTime: this.searchFormGroup.get('pickTime').value,
      dropTime: this.searchFormGroup.get('dropTime').value
    });

  }

  onSubmit() {

    const pickTime = this.searchFormGroup.get('pickTime').value;
    const pickDate = this.searchFormGroup.get('pickDate').value;

    const dropTime = this.searchFormGroup.get('dropTime').value;
    const dropDate = this.searchFormGroup.get('dropDate').value;

    const timeP = pickTime.split(':');
    const timeD = dropTime.split(':');

    const dateP = new Date(pickDate.year, pickDate.month - 1, pickDate.day, timeP[0], timeP[1], 0).toLocaleString();
    const dateD = new Date(dropDate.year, dropDate.month - 1, dropDate.day, timeD[0], timeD[1], 0).toLocaleString();


    console.log(this.searchFormGroup.value);

    this.params = {
      'search' : this.searchFormGroup.get('content').value,
      'pickup' : dateP,
      'dropoff' : dateD
    };

    console.log(this.params);

    this.router.navigate(['/search'], { queryParams: this.params });

  }

  search() {
  }



}
