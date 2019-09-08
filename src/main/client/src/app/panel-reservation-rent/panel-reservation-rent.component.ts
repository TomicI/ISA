import { Component, OnInit, ViewChild } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgbCalendar, NgbDate, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../services/user.service';
import { FilijalaService } from '../services/filijala.service';
import { RentacarService } from '../services/rentacar.service';
import {RentACar, Filijala, RezervacijaRent, Vozilo} from '../model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import {ReservationService} from "../services/reservation.service";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-panel-reservation-rent',
  templateUrl: './panel-reservation-rent.component.html',
  styleUrls: ['./panel-reservation-rent.component.css'],
  providers: [FilijalaService, RentacarService]
})
export class PanelReservationRentComponent implements OnInit {

  @ViewChild('content') private content;
  @ViewChild('paginator1') paginator: MatPaginator;
  @ViewChild('paginator2') paginator2: MatPaginator;

  statusGroup: FormGroup;

  modalRef: any;

  reservationName;

  displayedColumnsReservation: string[] = [
    'no',
    'veh',
    'pickup',
    'pickupB',
    'dropoff',
    'dropoffB',
    'price',
    'status',
    'edit'
  ];

  displayedColumnsReservationDeal: string[] = [
    'no',
    'veh',
    'pickup',
    'pickupB',
    'dropoff',
    'dropoffB',
    'price',
    'deal'
  ];

  rentACar: RentACar;

  reservationRent:RezervacijaRent;


  filData: Filijala[];

  rezData: RezervacijaRent[] = [];
  rezDataDeal: RezervacijaRent[] = [];


  resSource;
  resDealSource;


  rezervacije: RezervacijaRent[];

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private router: Router,
              private rentacarService: RentacarService,
              private filijalaService: FilijalaService,
              private userServise: UserService,
              private reservationService:ReservationService,
              private modalService: NgbModal,
              private fb: FormBuilder

              ) { }

  ngOnInit() {
    this.tableInit();


  }



  tableInit() {
    this.userServise.getUserRentACar().subscribe(data=>{
      this.rentACar = data;
      console.log(data);
    });

    this.reservationService.getAllAdmin(true).subscribe(data=>{

      console.log(data);
      this.rezData = data;
      this.resSource = new MatTableDataSource(this.rezData);
      this.resSource.paginator=this.paginator;
    });

    this.reservationService.getAllAdmin(false).subscribe(data=>{
      console.log(data);
      this.rezDataDeal = data;
      this.resDealSource = new MatTableDataSource(this.rezDataDeal);
      this.resDealSource.paginator=this.paginator2;
    });


  }


  editReservation(reservation){

    this.reservationRent = reservation;

    this.reservationName = reservation.voziloDTO.naziv;

    this.statusGroup = this.fb.group({
      'status':reservation.status
    });

    this.modalRef = this.modalService.open(this.content);

  }


  addDeal(){

    this.router.navigate(['newDeal']);


  }

  submitStatus(){

    this.reservationRent.status = this.statusGroup.value;

    console.log(this.reservationRent);



  }



}
