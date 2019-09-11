import { Component, OnInit } from '@angular/core';
import {Aviokompanija, RentACar} from "../model";
import {RentacarService} from "../services/rentacar.service";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css'],
  providers: [RentacarService,AviokompanijaService]
})
export class ListComponent implements OnInit {

  rentACars: RentACar[];
  avio:Aviokompanija[];

  params:any;

  constructor(private rentACarService: RentacarService,
              private aviokompanijaService: AviokompanijaService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {

    this.initRent();

    this.initAero();


  }

  initAero(){

    this.aviokompanijaService.getAll().then(data=>{
      this.avio = data;
    });
  }

  initRent(){
    this.rentACarService.getAll().subscribe(data=>{
      this.rentACars= data;
    });
  }

  onChangeVeh(value){

    console.log(value);

    if (value=='all'){
      this.initRent();
    }else if (value=='asc'){
      this.rentACars.sort(((a, b) => a.naziv.localeCompare(b.naziv)));

    }else if (value=='des'){
      this.rentACars.sort(((a, b) => b.naziv.localeCompare(a.naziv)));
    }

  }

  onChangeNameAir(value){


    if (value=='all'){
      this.initAero();
    }else if (value=='asc'){
      this.avio.sort(((a, b) => a.naziv.localeCompare(b.naziv)));

    }else if (value=='des'){
      this.avio.sort(((a, b) => b.naziv.localeCompare(a.naziv)));
    }else if (value=='min'){
      this.avio.sort(((a, b) => a.prosecnaOcena - b.prosecnaOcena));

    }else if (value=='max'){
      this.avio.sort(((a, b) => b.prosecnaOcena - a.prosecnaOcena));
    }

  }

  rentLink(rnt){

    console.log(rnt);
    this.params ={
      'id': rnt.id,
      'rent':rnt.naziv
    };
    this.router.navigate(['travel/rentacar/branch'], { queryParams: this.params });


  }

  avioLink(avio){

    this.router.navigate(['aviokompanija/',avio.id]);

  }


}
