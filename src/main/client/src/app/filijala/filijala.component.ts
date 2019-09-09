import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { FilijalaService } from '../services/filijala.service';
import {Filijala, RentACar, Rezervacija, RezervacijaRent} from '../model';
import { RentacarService } from '../services/rentacar.service';
import {CommunicationService} from "../services/communication.service";
import {ReservationService} from "../services/reservation.service";

@Component({
  selector: 'app-filijala',
  templateUrl: './filijala.component.html',
  styleUrls: ['./filijala.component.css'],
  providers: [FilijalaService,ReservationService]
})

export class FilijalaComponent implements OnInit {

  params: any = {};


  paramsUrl:any = {};

  naziv = '';

  filijale: Filijala[];

  reservationsRent : RezervacijaRent[]=[];


  constructor(
    private filijalaService: FilijalaService,
    private rentacarService: RentacarService,
    private reservationService: ReservationService,
    private communicationService: CommunicationService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {



    this.route.queryParams.subscribe(params => {

      this.paramsUrl = params;

      this.rentacarService.getAllFilijale(params.id).subscribe(data => {

          this.filijale = data;
          this.naziv = this.filijale[0].rentACarDTO.naziv;



      });

      this.communicationService.emitChange(params);

      if(params.pickup && params.dropoff){

        this.reservationService.getAllDeals(params).subscribe(data=>{
          console.log(data);
          this.reservationsRent = data;
        });

      }

    });


  }

  resDeal(res){

    let resTemp:Rezervacija = new Rezervacija(this.paramsUrl.res,null,null,null,res,null);

    console.log(resTemp);

    this.router.navigate(['travel/rentacar/reservation']).then(()=>{
      this.communicationService.reservationChange(resTemp);
    });

  }

  showVeh(fil) {

    this.params = {
      'id': fil.id,
      
    };

    this.router.navigate(['travel/rentacar/vehicle'], { queryParams: this.params });

  }




}
