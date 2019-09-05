import { Component, OnInit, ViewChild } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgbCalendar, NgbDate, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../services/user.service';
import { FilijalaService } from '../services/filijala.service';
import { RentacarService } from '../services/rentacar.service';
import { RentACar, Filijala, RezervacijaRent } from '../model';
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
  @ViewChild('paginator1') paginator: MatPaginator;
  @ViewChild('paginator2') paginator2: MatPaginator;



  displayedColumnsReservation: string[] = [
    'no',
    'veh',
    'resdate',
    'pickup',
    'pickupB',
    'dropoff',
    'dropoffB',
    'price',
    'status'
  ];

  displayedColumnsReservationDeal: string[] = [
    'no',
    'veh',
    'resdate',
    'pickup',
    'pickupB',
    'dropoff',
    'dropoffB',
    'price',
    'deal'
  ];

  rentACar: RentACar;


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




  addDeal(){

    this.router.navigate(['newDeal']);


  }


}
