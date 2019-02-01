import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { RentacarService } from '../services/rentacar.service';
import { RentACar, Filijala} from '../model';


@Component({
  selector: 'app-rentacar-list',
  templateUrl: './rentacar-list.component.html',
  styleUrls: ['./rentacar-list.component.css'],
  providers: [RentacarService],
})
export class RentacarListComponent implements OnInit {

  rentACars: Observable<RentACar[]>;
  filijala: Observable<Object>;

  constructor(
    private rentACarService: RentacarService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit() {
    this.getRentACars();
  }

  getRentACars() {
    this.rentACars = this.rentACarService.getAll();
  }

  getFilijala(id: number) {
    this.filijala = this.rentACarService.getAllFilijale(id);
  }

}
