import {Component, OnInit, Input, Output, EventEmitter, ViewChild} from '@angular/core';
import { ReservationService } from '../services/reservation.service';
import { TokenService } from '../auth/token.service';
import { Router } from '@angular/router';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {CommunicationService} from "../services/communication.service";

@Component({
  selector: 'app-res-detail',
  templateUrl: './res-detail.component.html',
  styleUrls: ['./res-detail.component.css']
})
export class ResDetailComponent implements OnInit {


  res: any;
  @Input() view:boolean;
  @Output() clickedCen = new EventEmitter<boolean>();
  @Output() clickedRate = new EventEmitter<any>();


  test;


  message = '';


  constructor(private tokenStorage: TokenService,
              private communicationService:CommunicationService,
              private reservationService: ReservationService ,
              private router: Router,private modalService: NgbModal,
              private formBuilder: FormBuilder) {


      this.communicationService.reservationEmitted$.subscribe(data=>{

        console.log("Prosledjena rezervacija!");

        console.log(data);

        this.res = data;

      });

  }

  ngOnInit() {


  }

  async rent() {
    console.log('Klik')

    if (this.tokenStorage.getToken()) {

      this.reservationService.saveRes(this.res).then(data => {
        alert('Reservation was made!');

      });


      setTimeout(() => {
        this.message = '';
        this.clickedCen.emit(true);
        this.router.navigate(['homeReg']);
      }, 4000);

      

    } else {
      alert('You have to sign in!')
      setTimeout(() => {
         this.message = '';
      }, 4000);
      
    }
  }



}
