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

  constructor(private modalService: NgbModal, private userService: UserService, private resService: ReservationService) { }

  ngOnInit() {


  }





}
