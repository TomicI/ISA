import { Component, OnInit, ChangeDetectorRef, ViewChild, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { RentACar, Filijala, Vozilo } from '../model';

import { UserService } from '../services/user.service';
import { RentacarService } from '../services/rentacar.service';
import { FilijalaService } from '../services/filijala.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';




@Component({
  selector: 'app-panel-admin-rent',
  templateUrl: './panel-admin-rent.component.html',
  styleUrls: ['./panel-admin-rent.component.css'],
  providers: [FilijalaService, RentacarService]
})
export class PanelAdminRentComponent implements OnInit {

  @Input() showModal: boolean;
  @Input() title: string;
  @Input() subTitle: string;
  @Input() cancelLabel: string;
  @Input() positiveLabel: string;


  @ViewChild('content') private content;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  filijalaFormGroup: FormGroup;

  string: any;

  proslo = false;

  edit = false;

  rentACar: RentACar;

  filijale: Observable<Object>;

  vozila: Observable<any>;

  broj: number;

  myTable: Observable<any>;

  voz: any[];

  dataTable: any;

  displayedColumns: string[] = [
    'no',
    'naziv',
    'marka',
    'model',
    'brojSedista',
    'brojVrata',
    'brojTorbi',
    'gorivo',
    'menjac',
    'klima',
    'rezervoar',
    'potrosnja',
    'dodatniopis',
    'prosecnaOcena',
    'action'];

  displayedColumnsFil: string[] = [
    'no',
    'adresa',
    'prosecnaOcena',
    'vehicles',
    'edit'];

  data: Vozilo[] = [];

  dataSource = new MatTableDataSource(this.data);

  filData: Filijala[] = [];

  filijalaSource = new MatTableDataSource(this.filData);

  constructor(
    private userServise: UserService,
    private rentacarService: RentacarService,
    private filijalaService: FilijalaService,
    private fb: FormBuilder,
    private modalService: NgbModal
  ) {}


  getMyTables(id: number) {

    this.myTable = this.filijalaService.getVozilaTabela(id);
    console.log(this.myTable);
  }

  ngOnInit() {

    this.dataSource.paginator = this.paginator;

    this.userServise.getUserRentACar().subscribe((rentacar: any) => {
      if (rentacar) {
        this.rentACar = rentacar;

        this.rentacarService.getAllFilijale(this.rentACar.id).subscribe(data => {

          if (data) {
            this.filData = data;
            console.log(data);
            this.filijalaSource = new MatTableDataSource(this.filData);
          } else {
            console.log('Filijale not found!');
          }

        });


      } else {
        console.log('RentACar not found!');
      }
    });



  }

  clickedRow(id: number) {

    

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  clickAction(id) {



  }

  showVehicles(filijala){
    console.log(filijala.id);
    this.filijalaService.getVozilaTabela(filijala.id).subscribe(data => {
      console.log(data);
      this.data = data;
      this.dataSource = new MatTableDataSource(this.data);

    });
  }

  editFilijala(filijala) {
    console.log(filijala);

    this.filijalaFormGroup = this.fb.group({
      adresa: [filijala.adresa]
    });


    this.proslo = true;
    this.modalService.open(this.content);
  }

  addFilijala(){

  
  }

  onCliked() {
    console.log('Submit kliknut!');
    console.log(this.filijalaFormGroup);
  }

  

}
