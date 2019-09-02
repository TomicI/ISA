import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AerodromSService} from '../aerodrom-s/aerodrom-s.service';
import {Aerodrom, KonfiguracijaLeta, Let, Lokacija, VrstaLeta} from '../model';
import {LetService} from '../letService/let.service';
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";
import {NgbCalendar, NgbDate} from "@ng-bootstrap/ng-bootstrap";

@Component({
    selector: 'app-form-add-let',
    templateUrl: './form-add-let.component.html',
    styleUrls: ['./form-add-let.component.css'],
    providers: [AerodromSService, LetService, AviokompanijaService]
  })
  export class FormAddLetComponent implements OnInit {
    @Input() avikompanija:number
    regFormA: FormGroup;
    submitted = false;
    aerodromi: Aerodrom[]=[];
    destinacije: Lokacija[]=[];
    vrstaLeta: VrstaLeta;
    aerodrom: Aerodrom;
    desstinacija: Lokacija;
    konfiguracija: KonfiguracijaLeta;

    konfig: KonfiguracijaLeta[]=[];

    let: Let;
    pom: Let;
    pom1: any;
    date:Date;

    arrayOfHours:any[] = ['00:00', '00:30', '01:00', '01:30', '02:00', '02:30', '03:00', '03:30', '04:00', '04:30', '05:00', '05:30',
      '06:00', '06:30', '07:00', '07:30', '08:00', '08:30', '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00', '12:30', '13:00'
      , '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00', '19:30'
      , '20:00', '20:30', '21:00', '21:30', '22:00', '22:30', '23:00', '23:30'];

    arrayOfHoursP: any[] = [];

    arrayOfHoursD: string[] = ['00:00', '00:30', '01:00', '01:30', '02:00', '02:30', '03:00', '03:30', '04:00', '04:30', '05:00', '05:30',
      '06:00', '06:30', '07:00', '07:30', '08:00', '08:30', '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00', '12:30', '13:00'
      , '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00', '19:30'
      , '20:00', '20:30', '21:00', '21:30', '22:00', '22:30', '23:00', '23:30'];

    fromDate: NgbDate;
    toDate: NgbDate;




    constructor(
      private router: Router,
      private route: ActivatedRoute,
      private formBuilder: FormBuilder ,
      private aerodromService: AerodromSService,
      private letService:LetService,
      private calendar: NgbCalendar,
      private aviokompanijaService: AviokompanijaService
    ) { }

    ngOnInit() {
      this.date = new Date();

      for (var temp of this.arrayOfHours){
        this.date = new Date();
        const timeP = temp.split(':');
        this.date.setHours(timeP[0]);
        this.date.setMinutes(timeP[1]);


        if (this.date > new Date()){
          console.log(this.date);
          this.arrayOfHoursP.push(temp);
        }

      }

      this.fromDate = this.calendar.getToday();
      this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 1);




      this.aerodromService.getAllAerodromi().then(pom=>
      {
        console.log("aerodromi");
        console.log(pom);
        this.aerodromi=pom;
      })

      this.letService.getAllLokacije().then(pom=>{this.destinacije=pom})

      this.route.params.subscribe
        ( params =>  { const id = params['id'];
        if (id) {
          this.aviokompanijaService.getKonfiguracije(id).then(pom=>
          {
            this.konfig=pom;
          })
        }});
      this.regFormA=this.formBuilder.group({
        vremePolaska: [''],
        vremeDolaska: [''],
        vremePutovanja: [''],
        duzinaPutovanja: [''],
        aerodrom:[''],
        destinacija: [''],
        presedanja:[''],
        brojPresedanja:[''],
        konfiguracijaLeta: [''],
        opis:[''],
        vrstaLeta:[''],
        timePolaska: [''],
        timeDolaska: ['']
      })
    }

    onSubmit(){
      this.submitted=true;
      //this.aerodrom.nazivAerodroma=this.regFormA.value.nazivAerodroma;
      //this.let=this.regFormA.value;
      //this.regFormA.value.aerodrom=this.let.aerodrom;
     /* console.log("presedanje " + this.pom1.id);
      console.log(this.pom1);
      this.regFormA.value.presedanje=this.pom1.id;*/

      // this.let.vremePolaska.setHours(this.regFormA.value.timePolaska.split(":")[0]);

      /*this.date = new Date(this.regFormA.value.vremePolaska);
      this.let.vremePolaska = new Date(this.date.getFullYear(), this.date.getMonth(), this.date.getDate(), this.regFormA.value.timePolaska.split(":")[0], this.regFormA.value.timePolaska.split(":")[1], 0, 0);
      console.log("Vreme poslaska F ");
      console.log(this.regFormA.value.vremePolaska);

      console.log("Vreme poslaska L");
      console.log(this.let.vremePolaska);*/

      //this.date=new Date(this.regFormA.value.vremeDolaska);
      // this.let.vremePolaska.setMinutes(this.regFormA.value.timePolaska.split(":")[1]);
      //this.let.vremeDolaska=new Date(this.date.getFullYear(), this.date.getMonth(), this.date.getDate(), this.regFormA.value.timeDolaska.split(":")[0], this.regFormA.value.timeDolaska.split(":")[1], 0,0);
     // this.let.vremeDolaska.setHours(this.regFormA.value.timeDolaska.split(":")[0]);
     //this.let.vremeDolaska.setMinutes(this.regFormA.value.timeDolaska.split(":")[1]);



     console.log("Vrednsoti let ");
     console.log(this.let);

      const pickTime = this.regFormA.get('timePolaska').value;
      const pickDate = this.regFormA.get('vremePolaska').value;

      const dropTime = this.regFormA.get('timeDolaska').value;
      const dropDate = this.regFormA.get('vremeDolaska').value;

      const timeP = pickTime.split(':');
      const timeD = dropTime.split(':');

      const dateP = new Date(pickDate.year, pickDate.month - 1, pickDate.day, timeP[0], timeP[1], 0);
      const dateD = new Date(dropDate.year, dropDate.month - 1, dropDate.day, timeD[0], timeD[1], 0);








      this.let = new Let(
        null,
        this.aerodrom,
        this.desstinacija,
        null,
        dateP,
        dateD,
        this.regFormA.get('presedanja').value,
        this.regFormA.get('brojPresedanja').value,
        this.regFormA.get('vremePutovanja').value,
        this.regFormA.get('duzinaPutovanja').value,
        0,
        this.konfiguracija,
        this.regFormA.get('opis').value,
        this.regFormA.get('vrstaLeta').value
      );

      console.log("pre slanja");
      console.log(this.let);

      this.letService.saveLet(this.let).then(pom=>{console.log("vratilo"); console.log(pom);})

     // console.log("ovoooo " + this.pom1.__zone_symbol__value.presedanje);
    }

    onChangeA(value: any){
      console.log(value);
      this.aerodromService.getAerodrom(value).then(pom=>{this.aerodrom=new Aerodrom(); this.aerodrom=pom;
        console.log(this.aerodrom);})
    }

    onChangeL(value: any){
      this.letService.getLokacija(value).then(pom=>{this.desstinacija=new Lokacija();this.desstinacija=pom; console.log(this.desstinacija)})

    }

    onChangeVL(value: any){
      console.log(value);
      if(value==1)
        this.regFormA.setValue({vrstaLeta : VrstaLeta.JEDAN_PRAVAC});
      if(value==2)
        this.regFormA.setValue({vrstaLeta : VrstaLeta.POVRATNI});
      if(value==3)
        this.regFormA.setValue({vrstaLeta : VrstaLeta.VISE_DESTINACIJA});
    }

    onChangeK(value: any){
      console.log(value);

      this.aviokompanijaService.getKonfiguracija(this.regFormA.get('konfiguracijaLeta').value).then(pom=>{this.konfiguracija=new KonfiguracijaLeta();this.konfiguracija=pom; console.log(this.konfiguracija)})

    }



  /*  onChange1(value: any){
      console.log(value);
      this.let.aerodrom.id=value;
    }*/

    dodajP(){
    /*  console.log("ima presedanje ");
      this.let.imaPresedanje=true;
      this.regFormA.value.imaPresedanje="true";
      this.onSubmit();
      
      this.router.navigateByUrl('letUpdate/'+(this.pom1.__zone_symbol__value.id-1));
     */
    }

    onDateSelected() {

      console.log('Promena');

      this.toDate = this.calendar.getNext(this.regFormA.get('vremePolaska').value, 'd', 1);
      this.regFormA.setValue({
        vremePolaska: this.regFormA.get('vremePolaska').value,
        vremeDolaska: this.regFormA.get('vremePolaska').value,
        timePolaska: this.regFormA.get('timePolaska').value,
        timeDolaska: this.regFormA.get('timeDolaska').value
      });

    }
  }
