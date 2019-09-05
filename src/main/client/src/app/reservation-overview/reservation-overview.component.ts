import {Component, OnInit, ViewChild} from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {UserService} from "../services/user.service";
import {ReservationService} from "../services/reservation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-reservation-overview',
  templateUrl: './reservation-overview.component.html',
  styleUrls: ['./reservation-overview.component.css']
})
export class ReservationOverviewComponent implements OnInit {

  @ViewChild('content') private content;

  cancelStatus = false;

  modalRef: any;

  message = '';

  tempRez;

  params:any;

  rentACarRezOld;

  rentACarRezNew;

  reservationsNew;

  reservationsOld;

  reservationsCanceled;

  constructor(private modalService: NgbModal, private userService: UserService, private resService: ReservationService,private router: Router,) { }

  ngOnInit() {

    this.initRez();

  }

  async initRez() {

 /*   await this.userService.getRes().then(data => {
      this.rentACarRezNew = data;
      this.cancelStatus = true;

    });

    await this.userService.getResHist().then(data => {
      this.rentACarRezOld = data;


    });*/

    this.resService.getUserReservation().subscribe(data=>{
      console.log(data);
      this.reservationsNew = data;

    });

    this.resService.getUserReservationHist().subscribe(data=>{
      console.log(data);
      this.reservationsOld = data;

    });

  }

  cancelVehRes(reservation) {
    this.tempRez = reservation;

    this.resService.getResCancel(reservation.id).then(data => {
      this.message = data.message;
      this.cancelStatus = true;
    }).catch(error => {
      this.message = error.error.message;
      this.cancelStatus = false;
    });

    this.modalRef = this.modalService.open(this.content);
  }

  resInfo(reservation){

    this.params = {
      'res':reservation.id
    };

    console.log(this.params);

    this.router.navigate(['homeReg/reservations/details'], { queryParams: this.params });

  }

  async confirmCancel() {
    await this.resService.confirmCancel(this.tempRez).then(data => {
      this.modalRef.close();
      this.initRez();
    }).catch(error => {
      this.message = error.error.message;
      this.cancelStatus = false;
    });
  }

  checkBeforeCanceled(res):boolean{
    console.log("Pozvana");
    if(res.rezervacijaRentACarDTO.otkazana){
      return false;
    }
    return true;
  }

}
