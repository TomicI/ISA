import {Component, OnInit, Input, Output, EventEmitter, ViewChild} from '@angular/core';
import { ReservationService } from '../services/reservation.service';
import { TokenService } from '../auth/token.service';
import { Router } from '@angular/router';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {CommunicationService} from "../services/communication.service";
import {Rezervacija} from "../model";

@Component({
  selector: 'app-res-detail',
  templateUrl: './res-detail.component.html',
  styleUrls: ['./res-detail.component.css']
})
export class ResDetailComponent implements OnInit {

  reservation:Rezervacija;

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

        this.reservation=data;
        this.res = data.rezervacijaRentACarDTO;

      });

  }

  ngOnInit() {


  }

  async rent() {
    console.log('Klik')

    if (this.tokenStorage.getToken()) {

      if (this.reservation.id){
          this.reservationService.addRes(this.reservation).subscribe(data=>{

            this.message='Reservation was added!';

            setTimeout(() => {
              this.message = '';
              this.clickedCen.emit(true);
              this.router.navigate(['homeReg']);
            }, 4000);

          });
      }else{
        this.reservationService.saveRes(this.res).then(data => {

          this.message='Reservation was made!';

          setTimeout(() => {
            this.message = '';
            this.clickedCen.emit(true);
            this.router.navigate(['homeReg']);
          }, 4000);

        });
      }




    } else {
      this.message = 'You have to sign in!';
      setTimeout(() => {
         this.message = '';
      }, 4000);
      
    }
  }



}
