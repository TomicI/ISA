import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RentacarService} from '../services/rentacar.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpParams} from "@angular/common/http";

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

  reservations: any[];

  filterFormGroup: FormGroup;

  message = '';

  constructor(private route: ActivatedRoute, private rentService: RentacarService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {


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

    this.route.queryParams.subscribe(param => {
      this.params = param;

      let params = new HttpParams();
      params = params.append('locationp',param.locationp);
      params = params.append('bring',param.bring);
      params = params.append('pickup',param.pickup);
      params = params.append('dropoff',param.dropoff);

      console.log(params);

      this.rentService.search(params).toPromise().then(data => {
        this.reservations = data;
        if (this.reservations.length != 0) {
          this.resBool = true;

        } else {
          this.criBool = true;
        }
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
    this.backBtn = false;

  }

  onSubmit() {

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



    console.log(this.params)






  }


}
