import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbDate, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { RentACar } from '../model';
import { RentacarService } from '../services/rentacar.service';
import { ActivatedRoute, Router } from '@angular/router';
import { startWith, map } from 'rxjs/operators';
import {Time} from "@angular/common";
import {forEach} from "@angular/router/src/utils/collection";
import {HttpParams} from "@angular/common/http";


export interface SearchGroup {
  rent: string;
  adress: string[];
}

export const _filter = (opt: string[], value: string): string[] => {
  const filterValue = value.toLowerCase();

  return opt.filter(item => item.toLowerCase().indexOf(filterValue) === 0);
};

@Component({
  selector: 'app-search-rent',
  templateUrl: './search-rent.component.html',
  styleUrls: ['./search-rent.component.css'],
  providers: [RentacarService],
})
export class SearchRentComponent implements OnInit {

  searchGroups: SearchGroup[] = [];
  searchGroups2: SearchGroup[] = [];

  options: string[] = [];
  filteredOptions: Observable<String[]>;

  searchGroupOptions: Observable<SearchGroup[]>;
  searchGroupOptions2: Observable<SearchGroup[]>;

  searchFormGroup: FormGroup;

  params: any = {};

  minDatum = this.calendar.getToday();

  arrayOfHours:any[] = ['00:00', '00:30', '01:00', '01:30', '02:00', '02:30', '03:00', '03:30', '04:00', '04:30', '05:00', '05:30',
    '06:00', '06:30', '07:00', '07:30', '08:00', '08:30', '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00', '12:30', '13:00'
    , '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00', '19:30'
    , '20:00', '20:30', '21:00', '21:30', '22:00', '22:30', '23:00', '23:30'];

  arrayOfHoursP: any[] = [];

  arrayOfHoursD: string[] = ['00:00', '00:30', '01:00', '01:30', '02:00', '02:30', '03:00', '03:30', '04:00', '04:30', '05:00', '05:30',
    '06:00', '06:30', '07:00', '07:30', '08:00', '08:30', '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00', '12:30', '13:00'
    , '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00', '19:30'
    , '20:00', '20:30', '21:00', '21:30', '22:00', '22:30', '23:00', '23:30'];

  fromDate: NgbDate;
  toDate: NgbDate;

  rentACars: RentACar[] = [];
  filijala: Observable<Object>;

  date:Date;


  constructor(
    private rentACarService: RentacarService,
    private route: ActivatedRoute,
    private calendar: NgbCalendar,
    private router: Router,
    private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.date = new Date();

    for (var temp of this.arrayOfHours){
      this.date = new Date();
      const timeP = temp.split(':');
      this.date.setHours(timeP[0]);
      this.date.setMinutes(timeP[1]);


      if (this.date > new Date()){
        console.log(this.date);
        this.arrayOfHoursP.push(temp);
      }

    }

    this.route.queryParams.subscribe(params => {
      console.log(params);
    });

    this.fromDate = this.calendar.getToday();
    this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 1);

    console.log(this.calendar.getToday());

    this.searchFormGroup = this.formBuilder.group({
      locationp: ['', [Validators.required]],
      bring: [''],
      pickDate: this.fromDate,
      dropDate: this.toDate,
      pickTime: this.arrayOfHoursP[0],
      dropTime: '12:00'

    });

    this.searchGroupOptions = this.searchFormGroup.get('locationp').valueChanges
      .pipe(
        startWith(''),
        map(value => this._filterGroup(value))
      );

    this.searchGroupOptions2 = this.searchFormGroup.get('bring').valueChanges
      .pipe(
        startWith(''),
        map(value => this._filterGroup2(value))
      );

    this.initAll();

  }

  async initAll() {
    await this.getRentACars();
    this.getFillAdress();

  }

  async getRentACars() {

    this.rentACars = await this.rentACarService.getAll().toPromise();

  }

  getFillAdress() {

    for (const rent of this.rentACars) {
      this.rentACarService.getAllFilijale(rent.id).toPromise().then(data => {
        this.getFill(data);

      });
    }
  }

  getFill(filijala) {


    let name = '';
    const adress = [];

    for (const fill of filijala) {
      name = fill.rentACarDTO.naziv;
      adress.push(fill.adresa);
    }

    const arr = {
      'rent': name,
      'adress': adress
    };

    this.searchGroups.push(arr);

    console.log(adress);


  }

  getFilijala(id: number) {
    this.filijala = this.rentACarService.getAllFilijale(id);
  }

  onDateSelected() {

    console.log('Promena');

    this.toDate = this.calendar.getNext(this.searchFormGroup.get('pickDate').value, 'd', 1);
    this.searchFormGroup.setValue({
      locationp: this.searchFormGroup.get('locationp').value,
      bring:this.searchFormGroup.get('bring').value,
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
      'locationp': this.searchFormGroup.get('locationp').value,
      'bring': this.searchFormGroup.get('bring').value,
      'pickup': dateP,
      'dropoff': dateD
    };



    this.router.navigate(['rentacar/search'], { queryParams: this.params });

  }

  search() {
  }

  autoPrvi(data){

    this.searchFormGroup.setValue({
      locationp: this.searchFormGroup.get('locationp').value,
      bring: this.searchFormGroup.get('locationp').value,
      pickDate: this.searchFormGroup.get('pickDate').value,
      dropDate: this.searchFormGroup.get('dropDate').value,
      pickTime: this.searchFormGroup.get('pickTime').value,
      dropTime: this.searchFormGroup.get('dropTime').value
    });
    
    console.log(data);
    for (const s of this.searchGroups){
      if(s.rent === data){
        this.searchGroups2.push(s);
      }
    }
    if (this.searchGroups2.length === 2){
      this.searchGroups2.shift();
    }
   
  }

  private _filterGroup(value: string): SearchGroup[] {
    if (value) {
      return this.searchGroups
        .map(group => ({ rent: group.rent, adress: _filter(group.adress, value) }))
        .filter(group => group.adress.length > 0);
    }

    return this.searchGroups;
  }

  private _filterGroup2(value: string): SearchGroup[] {
    if (value) {
      return this.searchGroups2
        .map(group => ({ rent: group.rent, adress: _filter(group.adress, value) }))
        .filter(group => group.adress.length > 0);
    }

    return this.searchGroups2;
  }



}
