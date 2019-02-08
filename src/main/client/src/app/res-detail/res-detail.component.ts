import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ReservationService } from '../services/reservation.service';
import { TokenService } from '../auth/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-res-detail',
  templateUrl: './res-detail.component.html',
  styleUrls: ['./res-detail.component.css']
})
export class ResDetailComponent implements OnInit {

  @Input() res: any;
  @Output() clickedCen = new EventEmitter<boolean>();
  

  message = '';

  constructor(private tokenStorage: TokenService, private reservationService: ReservationService , private router: Router) { }

  ngOnInit() {


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
        this.router.navigate(['rentacar']);
      }, 4000);

      

    } else {
      this.message = 'You have to sign in!';
      setTimeout(() => {
         this.message = '';
      }, 4000);
      
    }
  }

}
