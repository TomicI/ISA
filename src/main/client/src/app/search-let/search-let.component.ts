import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormControl} from "@angular/forms";
import {Aerodrom, Karta, KonfiguracijaLeta, Let, Lokacija, VrstaLeta} from "../model";
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
  searchFormGroup: FormGroup;
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
    this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 1);

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

          this.options1.push(element.naziv);
          console.log("lok");
          console.log(element.naziv);
      });

    })


    this.searchFormGroup=this.formBuilder.group({
      vremePolaska: [''],
      vremeDolaska: [''],
      opis:[''],
      presedanja: [''],
      brojPresedanja:[''],
      konfiguracijaLeta: [''],
      vrstaLeta:[null],
      duzinaPutovanja:['']
    })


    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );

    //this.myControl.valueChanges.pipe( startWith(''), map(value => this._filter(value))).subscribe(pom=>{console.log("pom"); console.log(pom); this.filteredOptions =pom;});
    // this.myControl1.valueChanges.pipe( startWith(''), map(value => this._filter(value))).subscribe(pom=>{console.log("pom"); console.log(pom); this.filteredOptions1 =pom;});
    this.filteredOptions1 = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter1(value))
      );


  }

  private _filter(value: string): String[] {
    if(this.options){
      const filterValue = value.toLowerCase();

      return this.options.filter(option => option.toLowerCase().includes(filterValue));
    }
  }

  private _filter1(value: string): String[] {
    if(this.options1){
      const filterValue = value.toLowerCase();

      return this.options1.filter(option => option.toLowerCase().includes(filterValue));
    }
  }

  onDateSelected() {

    console.log('Promena');

    this.toDate = this.calendar.getNext(this.searchFormGroup.get('vremePolaska').value, 'd', 1);
    this.searchFormGroup.setValue({
      vremePolaska: this.searchFormGroup.get('vremePolaska').value,
      vremeDolaska: this.searchFormGroup.get('vremePolaska').value
    });

  }

  onChangeVL(value: any){
    console.log(value);
    if(value==-1)
      this.searchFormGroup.setValue({vrstaLeta : null});
    if(value==1)
      this.searchFormGroup.setValue({vrstaLeta : VrstaLeta.JEDAN_PRAVAC});
    if(value==2)
      this.searchFormGroup.setValue({vrstaLeta : VrstaLeta.POVRATNI});
    if(value==3)
      this.searchFormGroup.setValue({vrstaLeta : VrstaLeta.VISE_DESTINACIJA});
  }

  onSubmit(){
    this.pretraga=this.searchFormGroup.value;
    this.pretraga.konfiguracijaLeta=new KonfiguracijaLeta();

    if(this.searchFormGroup.get('vrstaLeta').value=="-1")
      this.pretraga.vrstaLeta=null;

    console.log(this.pretraga);

    this.letService.pretraga(this.pretraga).then(pom=>{console.log("vratilo"); console.log(pom); this.rezultat=pom; this.tabela=true;})

  }

  sledeciKorak(l: number){
    this.router.navigateByUrl('karta/'+l);
  }
}
