import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { FilijalaService } from '../services/filijala.service';
import { Filijala, RentACar } from '../model';
import { RentacarService } from '../services/rentacar.service';

@Component({
  selector: 'app-filijala',
  templateUrl: './filijala.component.html',
  styleUrls: ['./filijala.component.css'],
  providers: [FilijalaService]
})

export class FilijalaComponent implements OnInit {

  params: any = {};

  naziv = '';

  filijale: Observable<Filijala[]>;


  constructor(
    private filijalaService: FilijalaService,
    private rentacarService: RentacarService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      this.rentacarService.getAllFilijale(params.id).subscribe(data => {
       
          this.filijale = data;
          console.log(data);
          if (data[0]) {
            this.naziv = data[0].rentACarDTO.naziv;
          }
          
        

      });


    });


  }

  showVeh(fil) {

    this.params = {
      'id': fil.id,
      
    };

    this.router.navigate(['rentacar/vehicle'], { queryParams: this.params });

  }




}
