import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Rezervacija} from "../model";
import {ReservationService} from "../services/reservation.service";

@Component({
  selector: 'app-reservation-details',
  templateUrl: './reservation-details.component.html',
  styleUrls: ['./reservation-details.component.css']
})
export class ReservationDetailsComponent implements OnInit {

  params: any = {};

  reservation;
  view:boolean;

  constructor(private route: ActivatedRoute,private reservationService:ReservationService) { }

  ngOnInit() {

    this.view=true;

    this.route.queryParams.subscribe(params => {
      this.params = params;

      this.reservationService.getUserReservationId(this.params.res).subscribe(data=>{
        this.reservation = data;
        console.log(data);
      },error1 => {

      });

      console.log(params);
    });

  }

  onClicked(event){

      console.log(event);
  }

}
