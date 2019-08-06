import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';
import { Destinacija } from '../model';

@Component({
  selector: 'app-destinacija',
  templateUrl: './destinacija.component.html',
  styleUrls: ['./destinacija.component.css'],
  providers: [AerodromSService]
})
export class DestinacijaComponent implements OnInit {

  destinacije: Observable<Destinacija[]>;
  constructor(private route: ActivatedRoute,
    private router: Router,
    private destinacijaService: AerodromSService ) { }

  ngOnInit() {
    this.getDestinacije();
  }

  getDestinacije(){

    //return this.destinacije=this.destinacijaService.getAllDest();
  }
}

