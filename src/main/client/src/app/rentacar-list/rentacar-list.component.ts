import { Component, OnInit, Input, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { RentacarService } from '../services/rentacar.service';
import { RentACar, Filijala } from '../model';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';
import { NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PARAMETERS } from '@angular/core/src/util/decorators';

@Component({
  selector: 'app-rentacar-list',
  templateUrl: './rentacar-list.component.html',
  styleUrls: ['./rentacar-list.component.css'],
  providers: [RentacarService],

})
export class RentacarListComponent implements OnInit {

  searchFormGroup: FormGroup;

  params: any = {};
  
  rentACars: Observable<RentACar[]>;
  filijala: Observable<Object>;

  constructor(
    private rentACarService: RentacarService,
    private route: ActivatedRoute,
    private calendar: NgbCalendar,
    private router: Router,
    private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.route.queryParams.subscribe(params => {
      console.log(params);
    });

    this.getRentACars();
  }

  getRentACars() {
    this.rentACars = this.rentACarService.getAll();
  }

  getFilijala(id: number) {
    this.filijala = this.rentACarService.getAllFilijale(id);
  }


  search() {
  }

  rentBranch(rnt) {
    console.log(rnt);
    this.params ={
      'id': rnt.id,
      'rent':rnt.naziv
    };
    this.router.navigate(['rentacar/branch'], { queryParams: this.params });
  }

}
