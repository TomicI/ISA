import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {Aerodrom, Aviokompanija, DodatnaUslugaAviokompanija, Let, Prtljag, Segment} from "../model";
import { ActivatedRoute, Router, Params } from '@angular/router';
import { AviokompanijaService } from '../aviokompanija/aviokompanija.service';
import {MatPaginator, MatTableDataSource} from "@angular/material";
import {LetService} from "../letService/let.service";

@Component({
  selector: 'app-aviokom-profil',
  templateUrl: './aviokom-profil.component.html',
  styleUrls: ['./aviokom-profil.component.css'],
  providers: [AviokompanijaService, LetService]
})
export class AviokomProfilComponent implements OnInit {

  @ViewChild('paginator1') paginator: MatPaginator;
  @ViewChild('paginator2') paginator2: MatPaginator;
  aviokomapnija: Aviokompanija;
  aerodromi: Aerodrom[]=[];
  aerodromSource;
  segmenti: Segment[]=[];
  letL: Let[]=[];
  cenovnikSource;
  prtljag: Prtljag[]=[];
  prtljagSource;
  dodatneUsluge: DodatnaUslugaAviokompanija[]=[];
  dodatnaUslugaSource;

  displayedColumns: string[] = [
    'no',
    'naziv',
    'adresa',
    'edit'];

  displayedColumnsC: string[] = [
    'no',
    'od',
    'do',
    'datum',
    'cena'];

  displayedColumnsP: string[] = [
    'no',
    'duzina',
    'sirina',
    'tezina',
    'cena'
    ];

  displeydColumnsD: string[]=[
    'no',
    'naziv',
    'opis',
    'cena'

  ];


  constructor(private route: ActivatedRoute,
              private router: Router,
              private aviokompanijaService: AviokompanijaService,
              private letService: LetService) { }

  ngOnInit() {
    this.aviokomapnija=new Aviokompanija();

    this.aviokompanijaService.getAviokompanijaAdmin().then(pom=>{
      if (pom) {
           this.aviokomapnija = pom;
           this.aviokompanijaService.getAerodorome(pom.id).then(pom=>{
             console.log("aerodromi");
             console.log(pom);
             this.aerodromi=pom;
             this.aerodromSource=new MatTableDataSource(this.aerodromi);
             this.aerodromSource.paginator = this.paginator;

             this.aviokompanijaService.getPrtljag(this.aviokomapnija.id).then(pom=>{
               this.prtljag=pom;
               this.prtljagSource=new MatTableDataSource(this.prtljag);
             }

             );

             this.aviokompanijaService.getDodatneUsluge(this.aviokomapnija.id).then(pom=>{
               this.dodatneUsluge=pom;
               this.dodatnaUslugaSource=new MatTableDataSource(this.dodatneUsluge);
             })
           })

      } else {
        console.log(`Aviokompanija not found `);
      }
    })

    this.aviokompanijaService.getLetoveAdmin().then(pom=>{
      console.log("letovi");
      console.log(pom);
      this.letL=pom;
      this.cenovnikSource=new MatTableDataSource(this.letL);
      this.cenovnikSource.paginator=this.paginator2;
    })

    this.letService.getAllSegmente().then(pom=>{
      console.log("segmenti");
      console.log(pom);
      this.segmenti=pom;
    })


  }


  addAerodrom(){
    this.router.navigateByUrl('aerodromAdd');
  }

  editAerodrom(aerodrom: Aerodrom){
    console.log("edit");
    console.log(aerodrom);
    this.router.navigateByUrl('aerodromUpdate/'+aerodrom.id);
  }

  getMinMaxCena(konf: number): string{
    console.log("konf");
    console.log(konf);
    var min = 0;
    var max = 0;
    var prvi=0;
      if(this.segmenti.length>1) {

        for (let i = 0; i < this.segmenti.length; i++) {
          if(this.segmenti[i].konfiguracija.id==konf){
            if(prvi ==0 ){
              min=this.segmenti[i].kategorija.cena;
              max=this.segmenti[i].kategorija.cena;
              prvi=1;
            }
            if(this.segmenti[i].kategorija.cena<min)
              min=this.segmenti[i].kategorija.cena;
            if(this.segmenti[i].kategorija.cena>max)
              max=this.segmenti[i].kategorija.cena;
          }

        }
      }
      if(min==0 &&  max==0){
        return '/';
      }
      if(min ==0){
        return max.toString();
      }
      if(max==0){
        return min.toString();
      }
      if(min==max){
        return max.toString();
      }
      if(min>0 && max>0){
        return min.toString()+"-"+max.toString();
      }
  }

  addPrtljag(){
    this.router.navigateByUrl('addPrtljag');
  }

  addDU(){
    this.router.navigateByUrl('addDodatnaUsluga');

  }
}


