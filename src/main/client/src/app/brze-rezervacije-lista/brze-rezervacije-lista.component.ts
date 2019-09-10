import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LetService} from "../letService/let.service";
import {Karta, Sediste} from "../model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-brze-rezervacije-lista',
  templateUrl: './brze-rezervacije-lista.component.html',
  styleUrls: ['./brze-rezervacije-lista.component.css'],
  providers: [LetService, UserService]
})
export class BrzeRezervacijeListaComponent implements OnInit {
  @Input() id: number;

  karte: Karta[]=[];
  sedista: Sediste[]=[];
  constructor(private route: ActivatedRoute,
              private router: Router,
              private letService: LetService,
              private userService: UserService) { }

  ngOnInit() {

    this.route.params.subscribe
    ( params => {
      const id = params['id'];
      if (id) {
        this.letService.getBrzeRezervacije(id).then(pom=>{

            this.karte = pom;
            console.log("vraceno");
            console.log(this.karte);
            for(let i=0; i<this.karte.length; i++){
              this.letService.getSedistaKarta(this.karte[i].id).then(pom=>{
                this.sedista.push(pom[0]);

              })
            }


        })
      }
    });
  }


  resDeal(i: number){
    this.letService.saveBrzaRezervacija(this.karte[i]).then(pom=>{
      console.log("vratilo");
      console.log(pom);
      window.alert("You reservation is created! ");
      this.userService.sendMail(pom).then(pom=>{
        console.log(pom);
      })
      this.router.navigateByUrl('/travel');
    })
  }
}
