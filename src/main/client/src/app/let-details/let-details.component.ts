import {Component, Input, OnInit} from '@angular/core';
import {DodatnaUslugaAviokompanija, Let, Prtljag, Segment} from "../model";
import {LetService} from "../letService/let.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";

@Component({
  selector: 'app-let-details',
  templateUrl: './let-details.component.html',
  styleUrls: ['./let-details.component.css'],
  providers: [LetService, AviokompanijaService]
})
export class LetDetailsComponent implements OnInit {
  @Input() id:number

  letL: Let;
  segmenti: Segment[]=[];
  prtljag: Prtljag[]=[];
  dodatneUsluge: DodatnaUslugaAviokompanija[]=[];
  constructor(private letService: LetService,
              private router: Router,
              private route: ActivatedRoute,
              private aviokompanijaService: AviokompanijaService) { }

  ngOnInit() {
    this.route.params.subscribe
    ( params =>  { const id = params['id'];
      if (id) {
        this.letService.getLet(id).then(pom=>{
          console.log("let");
          console.log(pom);
          this.letL=pom;

          this.aviokompanijaService.getPrtljag(this.letL.konfiguracijaLeta.aviokompanija.id).then(pom=>{
            console.log("prtljag");
            console.log(pom);
            this.prtljag=pom;
          })

          this.aviokompanijaService.getDodatneUsluge(this.letL.konfiguracijaLeta.aviokompanija.id).then(pom=>{
            console.log("dodatneUsl");
            console.log(pom);
            this.dodatneUsluge=pom;
          })

          this.letService.getSegmente(this.letL.konfiguracijaLeta.id).then(pom=> {

            console.log("segmeniti ");
            console.log(pom);
            this.segmenti = pom;

          })
        })
      }})

  }

  getMinMaxCena(): string{
    if(this.segmenti.length>1) {
      var min = this.segmenti[0].kategorija.cena;
      var max = this.segmenti[0].kategorija.cena;
      for (let i = 0; i < this.segmenti.length; i++) {
        if(this.segmenti[i].kategorija.cena<min)
          min=this.segmenti[i].kategorija.cena;
        if(this.segmenti[i].kategorija.cena>max)
          max=this.segmenti[i].kategorija.cena
      }
      return min.toString()+"-"+max.toString();
    }
    if(this.segmenti.length==1) {
      return this.segmenti[0].kategorija.cena.toString();
    }
    return '/';
  }

  rez(){
    this.router.navigateByUrl('karta/'+this.letL.id);
  }
}
