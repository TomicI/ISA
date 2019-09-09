import { Component, OnInit, Input, ChangeDetectionStrategy, Output, Directive, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';
import { Aerodrom } from '../model';
import { Let } from '../model';
import { LetService } from '../letService/let.service';
import { map } from 'rxjs/operators';


@Component({
  selector: 'app-let',
  templateUrl: './let.component.html',
  styleUrls: ['./let.component.css'],
  providers: [AerodromSService, LetService],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LetComponent implements OnInit {

  @Output()
    onChange : EventEmitter<void>= new EventEmitter<void>(); 


  letovi: Let[]=[];
  aerodromi: Aerodrom[]=[];
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private aerodromService: AerodromSService,
    private letService:LetService
  ) { }

  ngOnInit() {
    this.aerodromService.getAllAerodromi().then(pom=>{this.aerodromi=pom});

    this.letService.getAll().then(pom=>{
      this.letovi=pom;
      console.log(this.letovi);
    })


    
  }

  dodaj(i:number){
    this.router.navigateByUrl('brzaRezervacija/' + i );
  }
  /*
  
  findAodromP(aero: number, type){
    
    if(type=="undefined"){
      this.aerodromService.getAerodrom(aero).then(pom=>this.aerodromP=pom;)
      console.log(this.aerodromP)
      if(this.aerodromP=="undefined"){
        this.findAodromP(aero, "undefined");
      }
      return this.aerodromP.naziv;
    }else{
      console.log("ELSE ")
      this.aerodromService.getAerodrom(aero).subscribe(aerodrom=>
        this.aerodromP=aerodrom

      )
      if(this.aerodromP=="undefined"){
        this.findAodromP(aero, "undefined");
      }
      console.log(this.aerodromP)
      return this.aerodromP.naziv;
    }
    
    

  /*  this.aerodromi.forEach(aerodrom => {
      this.aerodromP=aerodrom as Aerodrom;
      console.log("ovo ");
      console.log(this.aerodromP.id);
      if(this.aerodromP.id==aero){
        console.log( "U if u " + this.aerodromP.id + "        " + this.aerodromP.nazivAerodroma);
          this.aerodromS=this.aerodromP.nazivAerodroma;
      }

      }
      
    )*/
  /*    this.aerodromService.getAerodrom(aero).subscribe((aerodrom: any) => {
        this.aerodromP$ = aerodrom;
      
        console.log(`Pronadjeno aerodrom P'${this.aerodromP$}' `);}
      );
       console.log(`Pronadjeno aerodrom kraj P'${this.aerodromP$}' `);
       return  this.aerodromP$.nazivAerodroma;*/
    //   return this.aerodromService.getAerodromIme(aero);
 // }
 /*
  findAerodromS(aero: number, type){
    if(type=="undefined"){
      this.aerodromService.getAerodrom(aero).subscribe(aerodrom=>
        this.aerodromS=aerodrom
      )
      console.log(this.aerodromS)
      if(this.aerodromS=="undefined"){
        this.findAerodromS(aero, "undefined");
      }
      return this.aerodromS.naziv;
    }else{
      console.log("ELSE ")
      this.aerodromService.getAerodrom(aero).subscribe(aerodrom=>
        this.aerodromS=aerodrom
      )
      if(this.aerodromS=="undefined"){
        this.findAerodromS(aero, "undefined");
      }
      console.log(this.aerodromS)
      return this.aerodromS.naziv;
    }
    
    /*this.aerodromService.getAerodrom(aero).subscribe((aerodrom:any) => {console.log(aerodrom)
      this.aerodromS$=aerodrom;
    });*/
 //  }

   
  
}
