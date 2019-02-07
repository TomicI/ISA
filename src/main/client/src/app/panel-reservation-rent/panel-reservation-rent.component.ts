import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-panel-reservation-rent',
  templateUrl: './panel-reservation-rent.component.html',
  styleUrls: ['./panel-reservation-rent.component.css']
})
export class PanelReservationRentComponent implements OnInit {


  displayedColumnsReservation: string[] = [
    'id',
    'resdate',
    'pickup',
    'dropoff',
    'price',
    ];

    resSource;

  constructor(private userService: UserService ) { }

  ngOnInit() {
    this.tableInit();
  }

  tableInit(){
   
  }
}
