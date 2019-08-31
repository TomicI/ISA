import {Component, Input, OnInit} from '@angular/core';
import {KonfiguracijaLeta} from "../model";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-konfig-list',
  templateUrl: './konfig-list.component.html',
  styleUrls: ['./konfig-list.component.css'],
  providers: [AviokompanijaService]
})
export class KonfigListComponent implements OnInit {
  @Input() id: number;
  konfig: KonfiguracijaLeta[]=[];
  constructor(
    private route: ActivatedRoute,
    private aviokomapnijaService: AviokompanijaService
  ) { }

  ngOnInit() {
    this.route.params.subscribe
    ( params => {
      this.id = params['id'];
      console.log("id " + this.id);
      this.aviokomapnijaService.getKonfiguracije(this.id).then(pom=>{this.konfig=pom;})
    })

  }

}
