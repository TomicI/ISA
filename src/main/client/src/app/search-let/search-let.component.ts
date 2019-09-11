import {Component, isDevMode, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormControl} from "@angular/forms";
import {Aerodrom, Karta, KonfiguracijaLeta, Let, Lokacija, Segment, VrstaLeta} from "../model";
import {NgbCalendar, NgbDate} from "@ng-bootstrap/ng-bootstrap";
import {ActivatedRoute, Router} from "@angular/router";
import {AerodromSService} from "../aerodrom-s/aerodrom-s.service";
import {LetService} from "../letService/let.service";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";
import {map, startWith} from 'rxjs/operators';
import {Observable} from "rxjs";

@Component({
  selector: 'app-search-let',
  templateUrl: './search-let.component.html',
  styleUrls: ['./search-let.component.css'],
  providers: [AerodromSService, LetService, AviokompanijaService]
})
export class SearchLetComponent implements OnInit {
  formSearch: FormGroup;
  aerodromi: Aerodrom[]=[];
  destinacije: Lokacija[]=[];
  vrstaLeta: VrstaLeta;
  konfiguracija: KonfiguracijaLeta;

  konfig: KonfiguracijaLeta[]=[];

  pretraga: Let;
  rezultat: Let[]=[];
  options: String[]=[];
  options1: String[]=[];
  filteredOptions: Observable<String[]>;
  filteredOptions1: Observable<String[]>;
  myControl = new FormControl();
  myControl1 = new FormControl();
  fromDate: NgbDate;
  toDate: NgbDate;
  tabela: boolean;
  karta: Karta;
  segmenti: Segment[]=[];
  constructor(
              private formBuilder: FormBuilder ,
              private aerodromService: AerodromSService,
              private letService:LetService,
              private calendar: NgbCalendar,
              private router: Router,
              private aviokompanijaService: AviokompanijaService) { }

  ngOnInit() {
    this.tabela=false;
    this.fromDate = this.calendar.getToday();
    this.toDate = this.calendar.getToday();

    this.aerodromService.getAllAerodromi().then(pom=>
    {
      console.log("aerodromi");
      console.log(pom);
      this.aerodromi=pom;
      this.aerodromi.forEach(element => {

          this.options.push(element.naziv);
        console.log("aer");
        console.log(element.naziv);

      });
    })

    this.letService.getAllLokacije().then(pom=>{
      this.destinacije=pom;
      this.destinacije.forEach(element => {

          this.options1.push(element.adresa);
          console.log("lok");
          console.log(element.adresa);
      });

    })

    this.letService.getAllSegmente().then(pom=>{
      this.segmenti=pom;
    })


    this.formSearch=this.formBuilder.group({
      vremePolaska: [''],
      vremeDolaska: [''],
      opis:[''],
      presedanja: [''],
      brojPresedanja:[''],
      konfiguracijaLeta: [''],
      vrstaLeta:[null],
      duzinaPutovanja:['']
    })


    this.filteredOptions = this.formSearch.get('opis').valueChanges
      .pipe(

        startWith(''),
        map(value => {return this._filter(value); })
      );

    //this.myControl.valueChanges.pipe( startWith(''), map(value => this._filter(value))).subscribe(pom=>{console.log("pom"); console.log(pom); this.filteredOptions =pom;});
    // this.myControl1.valueChanges.pipe( startWith(''), map(value => this._filter(value))).subscribe(pom=>{console.log("pom"); console.log(pom); this.filteredOptions1 =pom;});
    this.filteredOptions1 = this.formSearch.get('presedanja').valueChanges
      .pipe(
        startWith(''),
        map(value =>{ return this._filter1(value);} )
      );


  }

  private _filter(value: string): String[] {
    if(this.options){
      const filterValue = value.toLowerCase();
      return this.options.filter(option => option.toLowerCase().includes(filterValue));
    }
  }

  private _filter1(value: string): String[] {
    console.log(" pre fter1");
    if(this.options1){
      console.log("fter1");
      console.log(value);
      const filterValue = value.toLowerCase();

      return this.options1.filter(option => option.toLowerCase().includes(filterValue));
    }
  }

  onDateSelected() {

    console.log('Promena');

    this.toDate = this.formSearch.get('vremePolaska').value;
    this.formSearch.setValue({
      vremePolaska: this.formSearch.get('vremePolaska').value
    });

  }

  onChangeVL(value: any){
    console.log(value);
    if(value==-1)
      this.formSearch.setValue({vrstaLeta : null});
    if(value==1)
      this.formSearch.setValue({vrstaLeta : VrstaLeta.JEDAN_PRAVAC});
    if(value==2)
      this.formSearch.setValue({vrstaLeta : VrstaLeta.POVRATNI});
    if(value==3)
      this.formSearch.setValue({vrstaLeta : VrstaLeta.VISE_DESTINACIJA});


  }

  onSubmit(){
    this.pretraga=this.formSearch.value;
    this.pretraga.konfiguracijaLeta=new KonfiguracijaLeta();
    var vremePolaska=this.formSearch.get('vremePolaska').value;
    if(this.formSearch.get('vremeDolaska').value!=null) {
      var vremeDolaska = this.formSearch.get('vremeDolaska').value;
      const vDolaska = new Date(vremeDolaska.year, vremeDolaska.month - 1, vremeDolaska.day, 0, 0, 0);
      this.pretraga.vremeDolaska = vDolaska;
    }
    const vPolaska=new Date(vremePolaska.year, vremePolaska.month-1, vremePolaska.day, 0,0,0);

    this.pretraga.vremePolaska=vPolaska;


    if(this.formSearch.get('vrstaLeta').value=="-1")
      this.pretraga.vrstaLeta=null;

    console.log("Pretraga");
    console.log(this.pretraga);

    this.letService.pretraga(this.pretraga).then(pom=>{
      console.log("vratilo");
      console.log(pom);
      this.rezultat=pom;
      this.onChangeSort(11);
      this.tabela=true;
    })

  }

  sledeciKorak(l: Let){
    this.router.navigateByUrl('details/'+l.id);

  }

  getCena(id: number): string{

    for(let i=0; i<this.segmenti.length; i++){
      if(this.segmenti[i].konfiguracija.id==id) {
        return this.segmenti[i].kategorija.cena.toString();
      }
    }
    return '/';
  }

  onChangeSort(value: number){
    console.log("promena" + value);
    if(value==11){
      this.rezultat.sort((a, b) => a.konfiguracijaLeta.aviokompanija.naziv.localeCompare(b.konfiguracijaLeta.aviokompanija.naziv));
    }
    if(value==12){
      this.rezultat.sort((a, b) => b.konfiguracijaLeta.aviokompanija.naziv.localeCompare(a.konfiguracijaLeta.aviokompanija.naziv));
    }
    if(value==21){
      this.rezultat.sort((a, b) => a.vremePutovanja.localeCompare(b.vremePutovanja));
    }
    if(value==22){
      this.rezultat.sort((a, b) => b.vremePutovanja.localeCompare(a.vremePutovanja));
    }

    if(value==31){

      this.rezultat.sort((a, b) => this.getCena(b.konfiguracijaLeta.id).localeCompare(this.getCena(a.konfiguracijaLeta.id)));
    }
    if(value==32){

      this.rezultat.sort((a, b) => this.getCena(a.konfiguracijaLeta.id).localeCompare(this.getCena(b.konfiguracijaLeta.id)));
    }


  }
}
