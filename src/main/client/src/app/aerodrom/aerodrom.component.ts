import { Component, Input, ViewChild, NgZone, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router, Params } from '@angular/router';

import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';
import { Aerodrom, Lokacija } from '../model';
@Component({
  selector: 'app-aerodrom',
  templateUrl: './aerodrom.component.html',
  styleUrls: ['./aerodrom.component.css'],
  providers: [AerodromSService]
})
export class AerodromComponent implements OnInit {
  @Input() aerodrom: Aerodrom;

  destinacija: Lokacija;
  constructor(private route: ActivatedRoute,
    private router: Router,
    private aerodromService: AerodromSService) { }

  ngOnInit() {
    this.destinacija=new Lokacija();
    this.aerodrom=new Aerodrom();
    this.route.params.subscribe
    ( params =>  { const id = params['id'];
    if (id) {
      console.log(`Avikompanija with id '${id}' `);
      this.aerodromService.getAerodrom(id).then(aerodrom=>
      {if (aerodrom) {
        this.aerodrom = aerodrom;
        console.log(`Pronadjeno '${aerodrom.naziv}' `);
        /*   this.aerodromService.getDestinacija(this.aerodrom.destinacijaID).subscribe((destinacija: any) => {
             if (destinacija) {
               this.destinacija = destinacija;
               console.log(`Pronadjeno '${destinacija.nazivDestinacije}' `);
             }else{
               console.log(`Destinacija with id '${destinacija.id}' not found `);
             }
           })*/
      } else {
        console.log(`Aerodrom with id '${id}' not found `);
      }})
    }
  });


  }

}
