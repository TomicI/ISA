import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RentacarService} from '../services/rentacar.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpParams} from "@angular/common/http";
import {CommunicationService} from "../services/communication.service";
import {Rezervacija} from "../model";

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

  resBool = false;
  criBool = false;

  params:any;
  httpParams:HttpParams;

  reservations: any[];

  filterFormGroup: FormGroup;

  message = '';

  reservationPassed;

  constructor(private route: ActivatedRoute,
              private rentService: RentacarService,
              private formBuilder: FormBuilder,
              private communicationService: CommunicationService,
              private router: Router) {

    this.communicationService.reservationPassed$.subscribe(data=>{
      this.reservationPassed = data;
    });

  }

  ngOnInit() {


    this.initForm();


    this.route.queryParams.subscribe(param => {
      this.params = param;

      if (param.res){
        this.reservationPassed = param.res;
      }

      let params = new HttpParams();
      params = params.append('locationp',param.locationp);
      params = params.append('bring',param.bring);
      params = params.append('pickup',param.pickup);
      params = params.append('dropoff',param.dropoff);

      this.httpParams = params;


      this.rentService.search(params).toPromise().then(data => {
        this.reservations = data;

        this.initForm();

        if (this.reservations.length != 0) {
          this.resBool = true;
          this.criBool = false;
        } else {
          this.criBool = true;
        }
      });

    });

  }



  initForm(){

    this.filterFormGroup = this.formBuilder.group({
      mini: false,
      eco: false,
      stan: false,
      suv: false,
      prem: false,
      people: 'All',
      range: 'All',
      menjac: 'All'

    });

  }


  onCliked() {

    this.resPass = false;
    this.resView = true;
    this.backBtn = false;

  }

  rent(reservation) {

    let resTemp:Rezervacija = new Rezervacija(null,null,null,null,false,reservation,null);
    if (this.reservationPassed){
      resTemp.id = this.reservationPassed;
    }

    this.resChoose = reservation;
    this.router.navigate(['reservation']).then(()=>{
        this.communicationService.reservationChange(resTemp);
    });

    this.resPass = true;
    this.resView = false;
    this.backBtn = true;
  }

  back() {

    this.resPass = false;
    this.resView = true;
    this.backBtn = false;

  }

  onSubmit() {

    let params = new HttpParams();

    params = params.append('locationp',this.params.locationp);
    params = params.append('bring',this.params.bring);
    params = params.append('pickup',this.params.pickup);
    params = params.append('dropoff',this.params.dropoff);


    let group = '';

    console.log(this.filterFormGroup.value);

    let mini = this.filterFormGroup.get('mini').value;
    let eco = this.filterFormGroup.get('eco').value;
    let stan = this.filterFormGroup.get('stan').value;
    let suv = this.filterFormGroup.get('suv').value;
    let prem = this.filterFormGroup.get('prem').value;
    let people = this.filterFormGroup.get('people').value;
    let range = this.filterFormGroup.get('range').value;
    let menjac = this.filterFormGroup.get('menjac').value;

    if (mini){
        group+='-mini'
    }

    if (eco){
      group+='-eco'

    }
    if (stan){
      group+='-stan'
    }
    if (suv){
      group+='-suv'
    }
    if (prem){
      group+='-prem'
    }


    if (group!=''){
      params = params.append('group',group);
    }

    if (people!='All'){
      params = params.append('peo',people);
    }

    if (range!='All'){
      params = params.append('range',range);
    }

    if (menjac!='All'){
      params = params.append('gear',menjac);
    }

    console.log(params);

    this.rentService.search(params).toPromise().then(data => {
      this.reservations = data;
      if (this.reservations.length != 0) {
        this.criBool = false;
      } else {
        this.criBool = true;
      }
    });



  }

  onChangePrice(value){

  if (value=='min'){
      this.reservations.sort(((a, b) => a.cena - b.cena));

    }else if (value=='max'){
      this.reservations.sort(((a, b) => b.cena - a.cena));
    }

  }


}
