import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';
import { RezervacijaRent } from '../model';
import { ReservationService } from '../services/reservation.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.css']
})
export class UserReservationsComponent implements OnInit {
  @ViewChild('content') private content;

  cancelStatus = false;

  modalRef: any;

  message = '';

  tempRez;

  rentACarRezOld;

  rentACarRezNew;

  constructor(private modalService: NgbModal, private userService: UserService, private resService: ReservationService) { }

  ngOnInit() {

    this.initRez();

  }

  async initRez() {

    await this.userService.getRes().then(data => {
      this.rentACarRezNew = data;
      this.cancelStatus = true;

    });

    await this.userService.getResHist().then(data => {
      this.rentACarRezOld = data;


    });

  }

  cancelRes(reservation) {
    this.tempRez = reservation;

    this.resService.getResCancel(reservation.id).then(data => {
      this.message = data.message;
      this.cancelStatus = true;
    }).catch(error => {
      this.message = error.error.message;
    });

    this.modalRef = this.modalService.open(this.content);
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

}
