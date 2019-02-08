import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../services/user.service';
import { FilijalaService } from '../services/filijala.service';
import { RentacarService } from '../services/rentacar.service';
import { RentACar, Filijala, RezervacijaRent } from '../model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-panel-reservation-rent',
  templateUrl: './panel-reservation-rent.component.html',
  styleUrls: ['./panel-reservation-rent.component.css'],
  providers: [FilijalaService, RentacarService]
})
export class PanelReservationRentComponent implements OnInit {
  @ViewChild('paginator1') paginator: MatPaginator;

  displayedColumnsReservation: string[] = [
    'no',
    'resdate',
    'pickup',
    'dropoff',
    'price',
  ];
  rentACar: RentACar;

  filData: Filijala[];

  rezData: RezervacijaRent[] = [];

  resSource;

  rezervacije: RezervacijaRent[];

  constructor(private userService: UserService, private rentacarService: RentacarService,
    private filijalaService: FilijalaService, private userServise: UserService) { }

  ngOnInit() {
    this.tableInit();

  }

  async tableInit() {
    this.rentACar = await this.userServise.getUserRentACar().toPromise();
    await this.filijalaService.getallRez().toPromise().then(data => {
      this.rezData = data;

    });
    this.resSource = new MatTableDataSource(this.rezData);
    this.resSource.paginator=this.paginator;
  }






}
