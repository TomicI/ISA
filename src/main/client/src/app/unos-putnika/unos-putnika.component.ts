import {Component, Input, OnInit} from '@angular/core';
import {LetService} from "../letService/let.service";
import {Karta, Putnik, Rezervacija} from "../model";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-unos-putnika',
  templateUrl: './unos-putnika.component.html',
  styleUrls: ['./unos-putnika.component.css'],
  providers: [LetService]
})
export class UnosPutnikaComponent implements OnInit {
  @Input() kartaID:number; brPutika:number;
  karta: Karta;
  rezervacija: Rezervacija;
  pBrPuntika:number;
  brojSedista: number;
  unosPurnikaForm: FormGroup;

  constructor(private letService: LetService,
              private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder ) { }

  ngOnInit() {
    this.route.params.subscribe( params =>  { const id = params['kartaID']; const id1= params['brPutnika'];

      if (id) {
        this.letService.getRez(id).then(pom=>{

          this.rezervacija=pom;
          console.log("rez");
          console.log(pom);
          this.letService.getSedistaKarta(pom.kartaDTO.id).then(pom1=>{
            this.brojSedista=pom1.length;
            console.log("broj sedista karta " + pom1.length);
          })
        })
      }

      if(id1){
        this.pBrPuntika=id1;
        console.log("broj preostalih " + id1);
      }

    });

    this.unosPurnikaForm=this.formBuilder.group({
      brojPasosa:[''],
      ime:[''],
      prezime:['']
    });
  }

  onSubmit(){
    this.letService.savePutnik(this.unosPurnikaForm.value, this.rezervacija.kartaDTO.id).then(pom=> {
        console.log("vratilo ");
        console.log(pom);
      if (this.pBrPuntika - 1 > 0) {
        this.pBrPuntika = this.pBrPuntika - 1;
        this.router.navigateByUrl('unosPutnika/' + this.rezervacija.id + '/' + this.pBrPuntika);
      }else{
        window.alert("You reservation is created! ");
        this.router.navigateByUrl('/home');
      }
    });
  }

}
