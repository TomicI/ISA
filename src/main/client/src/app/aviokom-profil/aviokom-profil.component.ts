import {Component, Input, OnInit} from '@angular/core';
import { Aviokompanija} from "../model";
import { ActivatedRoute, Router, Params } from '@angular/router';
import { AviokompanijaService } from '../aviokompanija/aviokompanija.service';

@Component({
  selector: 'app-aviokom-profil',
  templateUrl: './aviokom-profil.component.html',
  styleUrls: ['./aviokom-profil.component.css'],
  providers: [AviokompanijaService]
})
export class AviokomProfilComponent implements OnInit {
  @Input() id: number;

  aviokomapnija: Aviokompanija;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private aviokompanijaService: AviokompanijaService) { }

  ngOnInit() {
    this.aviokomapnija=new Aviokompanija();
    this.route.params.subscribe
    ( params => {
      const id = params['id'];
      if (id) {
        console.log(`Avikompanija with id '${id}' `);
        this.aviokompanijaService.getAviokompanija(id).then(pom=>{
          if (pom) {
            this.aviokomapnija = pom;
            console.log(`Pronadjeno '${pom.naziv}' `);

          } else {
            console.log(`Aviokompanija with id '${id}' not found `);
          }
        })
      }
    });
  }
}


