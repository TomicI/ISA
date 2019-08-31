import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { AviokompanijaService } from '../aviokompanija/aviokompanija.service';
import { Aviokompanija } from '../model';

@Component({
  selector: 'app-aviokom-list',
  templateUrl: './aviokom-list.component.html',
  styleUrls: ['./aviokom-list.component.css'],
  providers: [AviokompanijaService]
})
export class AviokomListComponent implements OnInit {
	
	aviokompanije: Aviokompanija[]=[];
  constructor(private route: ActivatedRoute,
              private router: Router,
              private aviokompanijaService: AviokompanijaService) { }


  ngOnInit() {
    this.aviokompanijaService.getAll().then(pom=>{
      console.log(pom);
      this.aviokompanije=pom;
    });
  }


}
