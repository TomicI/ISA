import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RentacarService } from '../services/rentacar.service';

@Component({
  selector: 'app-search-get',
  templateUrl: './search-get.component.html',
  styleUrls: ['./search-get.component.css'],
  providers: [RentacarService]
})
export class SearchGetComponent implements OnInit {

  resChoose: any;

  resPass = true;

  resView = true;

  backBtn = false;

  params: any = {};

  reservations: any;

  message = '';

  constructor(private route: ActivatedRoute, private rentService: RentacarService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.params = params;
      this.rentService.search(this.params).toPromise().then(data => { 
        this.reservations = data ;
      });
      console.log(params);
    });

  }


  onCliked() {

    this.resPass = false;
    this.resView = true;
    this.backBtn = false;

  }

  rent(reservation) {
    this.resChoose = reservation;

    this.resPass = true;
    this.resView = false;
    this.backBtn = true;
  }

  back() {

    this.resPass = false;
    this.resView = true;
    this.backBtn=false;

  }

}
