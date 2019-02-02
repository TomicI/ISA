import { Component, OnInit, ChangeDetectorRef, ViewChild, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { RentACar, Filijala, Vozilo } from '../model';

import { UserService } from '../services/user.service';
import { RentacarService } from '../services/rentacar.service';
import { FilijalaService } from '../services/filijala.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormVozComponent } from '../form/form-voz/form-voz.component';




@Component({
  selector: 'app-panel-admin-rent',
  templateUrl: './panel-admin-rent.component.html',
  styleUrls: ['./panel-admin-rent.component.css'],
  providers: [FilijalaService, RentacarService]
})
export class PanelAdminRentComponent implements OnInit {

  @ViewChild('content') private content;
  @ViewChild('vehDiv') formVeh: FormVozComponent;
  @ViewChild('paginator1') paginator: MatPaginator;
  @ViewChild('paginator2') paginator2: MatPaginator;

  filijalaFormGroup: FormGroup;
  voziloFormGroup: FormGroup;

  string: any;

  modalRef: any;

  proslo = false;
  openVeh = false;

  edit = false;
  editVeh = false;

  rentACar: RentACar;

  filijale: Observable<Object>;

  vozila: Observable<Object>;

  filijala: Filijala;

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
  ) { }


  getMyTables(id: number) {

    this.myTable = this.filijalaService.getVozilaTabela(id);
    console.log(this.myTable);
  }

  ngOnInit() {
    this.tableInit();
  }

  tableInit() {


    this.userServise.getUserRentACar().subscribe((rentacar: any) => {
      if (rentacar) {
        this.rentACar = rentacar;

        this.rentacarService.getAllFilijale(this.rentACar.id).subscribe(data => {

          if (data) {
            this.filData = data;
            console.log(data);
            this.filijalaSource = new MatTableDataSource(this.filData);
            this.filijalaSource.paginator = this.paginator;
          } else {
            console.log('Filijale not found!');
          }

        });


      } else {
        console.log('RentACar not found!');
      }
    });

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  clickAction(id) {



  }

  showVehicles(filijala) {
    this.filijala = filijala;
    console.log(filijala.id);
    this.filijalaService.getVozilaTabela(filijala.id).subscribe(data => {
      console.log(data);
      this.data = data;
      this.dataSource = new MatTableDataSource(this.data);
      this.dataSource.paginator = this.paginator2;
    });
  }

  editFilijala(filijala) {
    console.log(filijala);

    this.edit = true;

    this.filijalaFormGroup = this.fb.group({
      filijala: this.fb.group(filijala)
    });
    this.openVeh = false;
    this.proslo = true;
    this.modalRef = this.modalService.open(this.content);
  }

  addFilijala() {

    console.log('Add');

    this.edit = false;

    this.filijalaFormGroup = this.fb.group({
      filijala: this.fb.group(new Filijala(null, null, null, this.rentACar))
    });
    this.openVeh = false;
    this.proslo = true;
    this.modalRef = this.modalService.open(this.content);
  }

  editVozilo(vozilo) {
    console.log(vozilo);
    console.log(this.filijala);

    this.editVeh = true;


    this.voziloFormGroup = this.fb.group({
      vozilo: this.fb.group(vozilo)
    });

    this.proslo = false;
    this.openVeh = true;
    this.modalRef = this.modalService.open(this.content);
  }

  addVozilo() {

    this.editVeh = false;

    this.voziloFormGroup = this.fb.group({
      vozilo: this.fb.group(new Vozilo(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null, null, null, null, null, this.filijala))
    });
    this.proslo = false;
    this.openVeh = true;
    this.modalRef = this.modalService.open(this.content);
  }

  onCliked() {
    console.log('Submit kliknut!');
    this.modalRef.close();
    this.tableInit();
    
  }


  onClickedVozilo() {
    console.log('Submit kliknut vozilo!');
    this.modalRef.close();
    this.showVehicles(this.filijala);
    
  }





}
