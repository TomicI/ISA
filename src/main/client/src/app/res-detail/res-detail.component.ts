import {Component, OnInit, Input, Output, EventEmitter, ViewChild} from '@angular/core';
import { ReservationService } from '../services/reservation.service';
import { TokenService } from '../auth/token.service';
import { Router } from '@angular/router';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-res-detail',
  templateUrl: './res-detail.component.html',
  styleUrls: ['./res-detail.component.css']
})
export class ResDetailComponent implements OnInit {

  @ViewChild('content') private content;

  @Input() res: any;
  @Input() view:boolean;
  @Output() clickedCen = new EventEmitter<boolean>();
  @Output() clickedRate = new EventEmitter<any>();

  rateGroup: FormGroup;
  clickedMap;

  test;

  modalRef: any;
  

  message = '';

  constructor(private tokenStorage: TokenService, private reservationService: ReservationService , private router: Router,private modalService: NgbModal,private formBuilder: FormBuilder) { }

  ngOnInit() {


    this.rateGroup = this.formBuilder.group({
      branchRate:new FormControl({value:"1",disabled:false}),
      vehicleRate: "1"
    });

  }

  async rent() {
    console.log('Klik')

    if (this.tokenStorage.getToken()) {

      this.reservationService.saveRes(this.res).then(data => {
        this.message = 'Reservation was made!';

      });


      setTimeout(() => {
        this.message = '';
        this.clickedCen.emit(true);
        this.router.navigate(['reservations']);
      }, 4000);

      

    } else {
      this.message = 'You have to sign in!';
      setTimeout(() => {
         this.message = '';
      }, 4000);
      
    }
  }

  rate(res){

    this.modalRef = this.modalService.open(this.content);

  }

  rateSubmit(){

    console.log(this.rateGroup.value);

    this.clickedRate.emit(this.rateGroup.value);

  }

  mapOpen(address){

    this.clickedMap = address;
  }

}
