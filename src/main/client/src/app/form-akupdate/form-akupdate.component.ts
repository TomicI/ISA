import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AviokompanijaService } from '../aviokompanija/aviokompanija.service';
import {Aviokompanija} from '../model';
import { ActivatedRoute, Router } from '@angular/router';
import { AviokomListComponent } from '../aviokom-list/aviokom-list.component';

@Component({
  selector: 'app-form-akupdate',
  templateUrl: './form-akupdate.component.html',
  styleUrls: ['./form-akupdate.component.css'],
  providers: [AviokompanijaService, AviokomListComponent]
})
export class FormAKUpdateComponent implements OnInit {
  
  @Input() aviokompanija: Aviokompanija;
  regFormU: FormGroup;
  submitted = false;
  

  constructor(
    private router: Router,
    private formBuilder: FormBuilder ,
    private aviokompanijaService: AviokompanijaService,
    private route: ActivatedRoute,
    private aviokompanije: AviokomListComponent
    ) { }

  ngOnInit() {
    this.regFormU=this.formBuilder.group({
      id: [''],
      naziv: [''],
      adresa: [''],
      opis: [''],
      prosecnaOcena:['']
    })
    console.log(`USLO update `);
    this.route.params.subscribe
      ( params =>  { const id = params['id'];
      if (id) {
        console.log(`Avikompanija with id '${id}' `);
        this.aviokompanijaService.getAviokompanija(id).then(aviokompanija=>{
          if (aviokompanija) {
            this.regFormU=this.formBuilder.group({
              id: aviokompanija.id,
              naziv: aviokompanija.naziv,
              adresa: aviokompanija.adresa,
              opis: aviokompanija.opis,
              prosecnaOcena: aviokompanija.prosecnaOcena
            })


          } else {
            console.log(`Avikompanija with id '${id}' not found `);
          }

        })

      }
    });
  }

  onSubmit(){
    this.submitted=true;
    console.log(this.regFormU.value);
    this.aviokompanijaService.updateAviokompanija(this.regFormU.value);
    this.router.navigateByUrl('aviokom-list');
  }


}
