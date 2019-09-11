import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";
import {Aviokompanija} from "../model";

@Component({
  selector: 'app-aviokomapnija-profil',
  templateUrl: './aviokomapnija-profil.component.html',
  styleUrls: ['./aviokomapnija-profil.component.css'],
  providers: [AviokompanijaService]
})
export class AviokomapnijaProfilComponent implements OnInit {
  @Input() id: number;

  aviokomapnija: Aviokompanija;
  adress: string;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private aviokompanijaService: AviokompanijaService) { }

  ngOnInit() {

    this.route.params.subscribe
    ( params => {
      const id = params['id'];
      if (id) {
        console.log(`Avikompanija with id '${id}' `);
        this.aviokompanijaService.getAviokompanija(id).then(pom=>{
          if (pom) {
            this.aviokomapnija = pom;
            console.log("vraceno");
            console.log(this.aviokomapnija);
            this.adress=this.aviokomapnija.lokacijaDTO.adresa;
          } else {
            console.log(`Aviokompanija with id '${id}' not found `);
          }
        })
      }
    });
  }

}
