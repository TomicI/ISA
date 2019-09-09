import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {NgbCalendar, NgbDate, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {map, startWith} from "rxjs/operators";
import {RentACar, Rezervacija, RezervacijaRent} from "../model";
import {UserService} from "../services/user.service";
import {RentacarService} from "../services/rentacar.service";
import {HttpParams} from "@angular/common/http";
import {ReservationService} from "../services/reservation.service";


export interface SearchGroup {
  rent: string;
  adress: string[];
}

export const _filter = (opt: string[], value: string): string[] => {
  const filterValue = value.toLowerCase();

  return opt.filter(item => item.toLowerCase().indexOf(filterValue) === 0);
};


@Component({
  selector: 'app-new-deal-reservation',
  templateUrl: './new-deal-reservation.component.html',
  styleUrls: ['./new-deal-reservation.component.css'],
  providers:[RentacarService]
})
export class NewDealReservationComponent implements OnInit {

  @ViewChild('content') private content;

  modalRef: any;


  param:any;

  reservations;

  priceGroup:FormGroup;

  reservation:RezervacijaRent;

  modal = 'New Deal';

  rentACar: RentACar;

  searchFormGroup: FormGroup;

  searchGroups: SearchGroup[] = [];
  searchGroups2: SearchGroup[] = [];

  searchGroupOptions: Observable<SearchGroup[]>;
  searchGroupOptions2: Observable<SearchGroup[]>;

  date: Date;

  arrayOfHours: any[] = ['00:00', '00:30', '01:00', '01:30', '02:00', '02:30', '03:00', '03:30', '04:00', '04:30', '05:00', '05:30',
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


  constructor(private userService: UserService,
              private rentacarService: RentacarService,
              private modalService: NgbModal,
              private formBuilder: FormBuilder,
              private calendar: NgbCalendar,
              private reservationService:ReservationService) {
  }

  ngOnInit() {

    this.getTimeForToday();

    this.fromDate = this.calendar.getToday();
    this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 1);



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

      this.tableInit();
  }


  tableInit() {
    this.userService.getUserRentACar().subscribe(data => {
      this.rentACar = data;
      console.log(data);
      this.getFillAdress();
    });
  }

  getTimeForToday() {
    this.arrayOfHoursP = [];
    this.date = new Date();

    for (var temp of this.arrayOfHours) {
      this.date = new Date();
      const timeP = temp.split(':');
      this.date.setHours(timeP[0]);
      this.date.setMinutes(timeP[1]);

      if (this.date > new Date()) {

        this.arrayOfHoursP.push(temp);
      }

    }
  }

  getFillAdress() {

    this.rentacarService.getAllFilijale(this.rentACar.id).subscribe(data => {
      this.getFill(data);

    });
  }

  getFill(filijala) {


    let name = '';
    const adress = [];

    for (const fill of filijala) {
      name = fill.rentACarDTO.naziv;
      adress.push(fill.lokacijaDTO.adresa);
    }

    const arr = {
      'rent': name,
      'adress': adress
    };

    this.searchGroups.push(arr);

    console.log(adress);


  }

  private _filterGroup(value: string): SearchGroup[] {
    if (value) {
      return this.searchGroups
        .map(group => ({rent: group.rent, adress: _filter(group.adress, value)}))
        .filter(group => group.adress.length > 0);
    }

    return this.searchGroups;
  }

  private _filterGroup2(value: string): SearchGroup[] {
    if (value) {
      return this.searchGroups2
        .map(group => ({rent: group.rent, adress: _filter(group.adress, value)}))
        .filter(group => group.adress.length > 0);
    }

    return this.searchGroups2;
  }

  autoPrvi(data) {

    this.searchFormGroup.setValue({
      locationp: this.searchFormGroup.get('locationp').value,
      bring: this.searchFormGroup.get('locationp').value,
      pickDate: this.searchFormGroup.get('pickDate').value,
      dropDate: this.searchFormGroup.get('dropDate').value,
      pickTime: this.searchFormGroup.get('pickTime').value,
      dropTime: this.searchFormGroup.get('dropTime').value
    });

    console.log(data);
    for (const s of this.searchGroups) {
      if (s.rent === data) {
        this.searchGroups2.push(s);
      }
    }
    if (this.searchGroups2.length === 2) {
      this.searchGroups2.shift();
    }

  }

  onDateSelected() {

    console.log('Promena');

    console.log(this.fromDate);
    console.log(this.searchFormGroup.get('pickDate').value);

    if (this.fromDate.equals(this.searchFormGroup.get('pickDate').value)) {
      console.log("Isti su ");
      this.getTimeForToday();
    } else {
      this.arrayOfHoursP = [];
      this.arrayOfHoursP = this.arrayOfHours;
    }

    this.toDate = this.calendar.getNext(this.searchFormGroup.get('pickDate').value, 'd', 1);
    this.searchFormGroup.setValue({
      locationp: this.searchFormGroup.get('locationp').value,
      bring: this.searchFormGroup.get('bring').value,
      pickDate: this.searchFormGroup.get('pickDate').value,
      dropDate: this.calendar.getNext(this.searchFormGroup.get('pickDate').value, 'd', 1),
      pickTime: this.searchFormGroup.get('pickTime').value,
      dropTime: this.searchFormGroup.get('dropTime').value
    });

  }

  onSubmit(){

    const pickTime = this.searchFormGroup.get('pickTime').value;
    const pickDate = this.searchFormGroup.get('pickDate').value;

    const dropTime = this.searchFormGroup.get('dropTime').value;
    const dropDate = this.searchFormGroup.get('dropDate').value;

    const timeP = pickTime.split(':');
    const timeD = dropTime.split(':');

    const dateP = new Date(pickDate.year, pickDate.month - 1, pickDate.day, timeP[0], timeP[1], 0).toLocaleString();
    const dateD = new Date(dropDate.year, dropDate.month - 1, dropDate.day, timeD[0], timeD[1], 0).toLocaleString();


    console.log(this.searchFormGroup.value);


    let params = new HttpParams();
    params = params.append('locationp',this.searchFormGroup.get('locationp').value);
    params = params.append('bring',this.searchFormGroup.get('bring').value);
    params = params.append('pickup',dateP);
    params = params.append('dropoff',dateD);

    this.rentacarService.search(params).subscribe(data => {
      this.reservations = data;
      console.log(data);
    });





  }

  resInfo(res){



    this.reservation = res;

    this.priceGroup = this.formBuilder.group({
      price:this.reservation.cena
    });

    this.modalRef = this.modalService.open(this.content);


  }

  addDeal(){

    this.reservation.popust = this.priceGroup.get('price').value;
    this.reservation.naPopustu = true;


    console.log('Klik')


      this.reservationService.saveRes(this.reservation).then(data => {
        //this.message = 'Reservation was made!';

        console.log(data);
        alert("Reservation was made!")
        this.onSubmit();
        this.modalRef.close();


      }).catch(err=>{
        alert(err);
      });



  }

}
