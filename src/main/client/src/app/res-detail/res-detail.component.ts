import {Component, OnInit, Input, Output, EventEmitter, ViewChild, HostListener} from '@angular/core';
import { ReservationService } from '../services/reservation.service';
import { TokenService } from '../auth/token.service';
import { Router } from '@angular/router';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {CommunicationService} from "../services/communication.service";
import {Rezervacija} from "../model";
import {Observable, of} from "rxjs";
import {DialogService} from "../security/dialog.service";

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

  clicked:boolean;

  message = '';


  constructor(private tokenStorage: TokenService,
              private communicationService:CommunicationService,
              private reservationService: ReservationService ,
              private router: Router,private modalService: NgbModal,
              private formBuilder: FormBuilder,
              public dialogService: DialogService) {


      this.communicationService.reservationEmitted$.subscribe(data=>{

        console.log("Prosledjena rezervacija!");

        console.log(data);

        this.reservation=data;
        this.res = data.rezervacijaRentACarDTO;

      });

  }

  canDeactivate(): Observable<boolean> | boolean {

    if (!this.clicked){
      return this.dialogService.confirm('Discard reservation?');
    }
    return false;

  }

  @HostListener('window:beforeunload', ['$event'])
  canLeavePage($event: any): Observable<boolean> {
    return this.dialogService.confirm('Discard reservation?');
  }

  ngOnInit() {


  }

  async rent() {
    console.log('Klik')

    if (this.tokenStorage.getToken()) {

      if (this.reservation.id){

          this.reservationService.addRes(this.reservation).subscribe(data=>{

            this.message='Reservation was added!';
            this.clicked = true;

            setTimeout(() => {
              this.message = '';
              this.clickedCen.emit(true);
              this.router.navigate(['homeReg']);
            }, 4000);

          });
      }else{
        this.reservationService.saveRes(this.res).then(data => {

          this.message='Reservation was made!';
          this.clicked = true;

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
