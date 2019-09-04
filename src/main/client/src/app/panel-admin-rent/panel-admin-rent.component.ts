import { Component, OnInit, ChangeDetectorRef, ViewChild, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { RentACar, Filijala, Vozilo, CenovnikRent } from '../model';

import { UserService } from '../services/user.service';
import { RentacarService } from '../services/rentacar.service';
import { FilijalaService } from '../services/filijala.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormVozComponent } from '../form/form-voz/form-voz.component';
import { DataSource } from '@angular/cdk/table';
import {VehicleService} from "../services/vehicle.service";


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
  @ViewChild('paginator3') paginator3: MatPaginator;

  filijalaFormGroup: FormGroup;
  voziloFormGroup: FormGroup;
  cenovnikFormGroup: FormGroup;
  rentFormGroup: FormGroup;

  modal = '';

  string: any;

  modalRef: any;

  proslo = false;
  openVeh = false;
  openCen = false;

  opcija = 0;

  edit = false;
  editVeh = false;
  editCen = false;

  minDate;

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
    'edit',
    'rate'];

  displayedColumnsFil: string[] = [
    'no',
    'adresa',
    'prosecnaOcena',
    'vehicles',
    'edit'];

  displayedColumnsCenovnik: string[] = [
    'no',
    'name',
    'odDatuma',
    'doDatuma',
    'price',
    'edit'];

  data: Vozilo[];

  dataSource;

  filData: Filijala[] = [];

  filijalaSource;

  cenData;

  cenovnikSource;

  allVozila: any[] = [];

  allFilId: any[];

  ob: Observable<any>;

  constructor(
    private userServise: UserService,
    private rentacarService: RentacarService,
    private filijalaService: FilijalaService,
    private vehicleService:VehicleService,
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

  async tableInit() {

    this.allVozila = [];

    this.rentACar = await this.userServise.getUserRentACar().toPromise();

    this.filData = await this.rentacarService.getAllFilijale(this.rentACar.id).toPromise();
    this.filijalaSource = new MatTableDataSource(this.filData);
    this.filijalaSource.paginator = this.paginator;


    //await this.getAllVozila();

    this.allVehicleRent();

    await this.getRates();


    // this.userServise.getUserRentACar().subscribe((rentacar: any) => {
    //   if (rentacar) {
    //     this.rentACar = rentacar;

    //     this.rentacarService.getAllFilijale(this.rentACar.id).subscribe(data => {

    //       if (data) {
    //         this.filData = data;
    //         console.log(data);
    //         this.filijalaSource = new MatTableDataSource(this.filData);
    //         this.filijalaSource.paginator = this.paginator;

    //       } else {
    //         console.log('Filijale not found!');
    //       }

    //     });

    //     this.rentacarService.getCenovnik(this.rentACar.id).subscribe(data => {

    //       if (data) {

    //         this.cenData = data;
    //         console.log(data);
    //         this.cenovnikSource = new MatTableDataSource(this.cenData);
    //         this.cenovnikSource.paginator = this.paginator3;


    //       } else {
    //         console.log('Cenovnik not found');
    //       }


    //     });


    //   } else {
    //     console.log('RentACar not found!');
    //   }

    // });

  }

  allVehicleRent(){

    if (this.filijala!=null){
      this.showVehicles(this.filijala);
    }else{
      this.vehicleService.allVehicleRent().subscribe(data=>{
        this.allVozila = data;
        this.data = data;
        this.dataSource = new MatTableDataSource(this.data);
        this.dataSource.paginator = this.paginator2;
        console.log(data);
      });
    }



  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
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

    this.modal = 'Edit branch ' + filijala.adresa;

    console.log(filijala);

    this.edit = true;

    this.filijalaFormGroup = this.fb.group({
      filijala: this.fb.group(filijala)
    });
    this.openVeh = false;
    this.proslo = true;
    this.openCen = false;

    this.modalRef = this.modalService.open(this.content);
  }


  addFilijala() {

    this.modal = 'New Branch';

    console.log('Add');

    this.edit = false;

    this.filijalaFormGroup = this.fb.group({
      filijala: this.fb.group(new Filijala(null, null, null, this.rentACar))
    });
    this.openVeh = false;
    this.proslo = true;
    this.openCen = false;
    this.modalRef = this.modalService.open(this.content);
  }

  onCliked() {
    console.log('Submit kliknut!');
    this.modalRef.close();
    this.tableInit();

  }

  async getAllVozila() {

    for (const fil of this.filData) {
      this.getVozLista(await this.filijalaService.getAllVozila(fil.id).toPromise());
    }

    this.data = this.allVozila;
    this.dataSource = new MatTableDataSource(this.data);
    this.dataSource.paginator = this.paginator2;

  }

  getVozLista(vozila) {
    for (const vozilo of vozila) {
      this.allVozila.push(vozilo);
    }

  }



  editVozilo(vozilo) {

    this.modal = 'Edit vehicle ' + vozilo.naziv;

    console.log(vozilo);
    console.log(this.filijala);

    this.editVeh = true;

    this.voziloFormGroup = this.fb.group({
      vozilo: this.fb.group(vozilo)
    });

    this.proslo = false;
    this.openVeh = true;
    this.openCen = false;
    this.modalRef = this.modalService.open(this.content);
  }

  addVozilo() {

    this.modal = 'New vehicle';

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
        null,
        null, null, null, null, null, this.filijala))
    });
    this.proslo = false;
    this.openVeh = true;
    this.openCen = false;
    this.modalRef = this.modalService.open(this.content);
  }


  onClickedVozilo() {
    console.log('Submit kliknut vozilo!');
    this.modalRef.close();
    //this.showVehicles(this.filijala);
    this.allVehicleRent();

  }

  async getRates() {

    this.cenData = await this.rentacarService.getCenovnik(this.rentACar.id).toPromise();
    this.cenovnikSource = new MatTableDataSource(this.cenData);
    this.cenovnikSource.paginator = this.paginator3;

  }

  addCenovnik(vozilo) {
    this.modal = 'New rate for ' + vozilo.naziv;

    this.editCen = false;

    this.cenovnikFormGroup = this.fb.group({
      CenovnikRent: this.fb.group(new CenovnikRent(
        null,
        null,
        null,
        null,
        vozilo,
        this.rentACar
      ))
    });

    this.proslo = false;
    this.openVeh = false;
    this.openCen = true;

    this.modalRef = this.modalService.open(this.content);

  }

  editCenovnik(cenovnik) {

    this.modal = 'Edit rate for ' + cenovnik.voziloDTO.naziv;

    this.editCen = true;
    this.cenovnikFormGroup = this.fb.group({
      CenovnikRent: this.fb.group(new CenovnikRent(
        cenovnik.id,
        new Date(cenovnik.odDatuma),
        new Date(cenovnik.doDatuma),
        cenovnik.cena,
        cenovnik.voziloDTO,
        cenovnik.rentACarDTO
      ))
    });


    this.proslo = false;
    this.openVeh = false;
    this.openCen = true;

    this.modalRef = this.modalService.open(this.content);

  }

  onClickedCenovnik() {
    console.log('Kliknut cenovnik');
    this.modalRef.close();
    this.getRates();
  }


}
