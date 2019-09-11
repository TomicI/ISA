import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Karta, Ocena, Rate, Rezervacija, RezervacijaRent, Vozilo} from "../model";
import {ReservationService} from "../services/reservation.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {VehicleService} from "../services/vehicle.service";
import {RatingService} from "../services/rating.service";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-reservation-details',
  templateUrl: './reservation-details.component.html',
  styleUrls: ['./reservation-details.component.css']
})
export class ReservationDetailsComponent implements OnInit {

  @ViewChild('content') private content;

  rateGroup: FormGroup;
  clickedMap;

  rateRent:boolean;
  rateAir:boolean;

  ratingRent:boolean;
  ratingAir:boolean;


  modalRef: any;

  rates:Rate;

  params: any = {};

  reservation:Rezervacija;
  rentACarRes:RezervacijaRent;
  karta:Karta;

  constructor(private route: ActivatedRoute,
              private reservationService:ReservationService,
              private router: Router,
              private modalService: NgbModal,
              private formBuilder: FormBuilder,
              private vehicleService:VehicleService,
              private ratingService:RatingService) { }

  ngOnInit() {

    this.rateGroup = this.formBuilder.group({
      branchRate:new FormControl({value:"1",disabled:false}),
      vehicleRate: "1",
      airRate:"1",
      flightRate:"1"
    });

    this.route.queryParams.subscribe(params => {
      this.params = params;

      this.reservationService.getUserReservationId(this.params.res).subscribe(data=>{
        this.reservation = data;

        let params = new HttpParams();
        params = params.append('resid',this.reservation.id.toString());

        this.ratingService.getAllRating(params).subscribe(data=>{
          this.rates=data;
        });

        if (data.rezervacijaRentACarDTO){
          this.rentACarRes=data.rezervacijaRentACarDTO;

          params = params.append('vehid',this.rentACarRes.voziloDTO.id.toString());
          params = params.append('filid',this.rentACarRes.filijalaDTO.id.toString());

          this.ratingService.getPermission(params).subscribe(data=>{
            this.rateRent = true;
          },error=>{
            this.rateRent = false;
          });

        }

        if(data.kartaDTO){

          this.karta = data.kartaDTO;

          this.ratingService.getPermissionAir(params).subscribe(data=>{
            this.rateAir = true;
          },error=>{
            this.rateAir = false;
          });


        }





       /* this.ratingService.getRating(params).subscribe(data=>{
          console.log(data);
        });*/



        console.log(data);
      },error1 => {

      });

      console.log(params);
    });




  }

  rateSubmit(){

    let ocenaVeh:Ocena;
    let ocenaBra:Ocena;

    ocenaVeh = new Ocena(
      null,
      this.rateGroup.get('vehicleRate').value,
      null,
      null,
      null,
      this.rentACarRes.voziloDTO,
      this.reservation.userDTO,
      null,
      this.reservation);

    ocenaBra = new Ocena(
      null,
      this.rateGroup.get('branchRate').value,
      null,
      null,
      null,
      null,
      this.reservation.userDTO,
      this.rentACarRes.filijalaDTO,
      this.reservation);

    let ocene:Ocena[];
    ocene = new Array();

    ocene.push(ocenaVeh);
    ocene.push(ocenaBra);

    console.log(ocene);

    this.ratingService.saveRentACarRating(ocene).subscribe(data=>{
      console.log(data);
      this.ngOnInit();
    },error1 => {
      alert(error1.error.message);
    } ) ;


    this.modalRef.close();

   /* this.vehicleService.rateVehicle(ocena).subscribe(data=>{
      console.log(data);
    },error1 => {
      console.log(error1);
    });*/

  }

  airSubmit(){

    let ocenaAir:Ocena;
    let ocenaFlight:Ocena;

    ocenaAir = new Ocena(
      null,
      this.rateGroup.get('airRate').value,
      null,
      this.karta.let.aerodrom.aviokompanija,
      null,
      null,
      null,
      null,
      this.reservation);

    ocenaFlight = new Ocena(
      null,
      this.rateGroup.get('flightRate').value,
      null,
      null,
      this.karta.let,
      null,
      null,
      null,
      this.reservation);

    let ocene:Ocena[];
    ocene = new Array();

    ocene.push(ocenaAir);
    ocene.push(ocenaFlight);

    console.log(ocene);

    this.ratingService.saveAirRating(ocene).subscribe(data=>{
      console.log(data);
      this.ngOnInit();
    },error1 => {
      alert(error1.error.message);
    } ) ;

    this.modalRef.close();

  }

  rate(res){

    this.ratingRent = true;
    this.ratingAir = false;

    this.modalRef = this.modalService.open(this.content);

  }

  rateAvio(){
    this.ratingRent = false;
    this.ratingAir = true;

    this.modalRef = this.modalService.open(this.content);


  }

  mapOpen(address){


  }

}
