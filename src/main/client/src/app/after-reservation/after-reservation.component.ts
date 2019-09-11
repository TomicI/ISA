import {Component, Input, OnInit} from '@angular/core';
import {LetService} from "../letService/let.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RentACar, Hotel, Rezervacija} from "../model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-after-reservation',
  templateUrl: './after-reservation.component.html',
  styleUrls: ['./after-reservation.component.css'],
  providers: [LetService, UserService]
})
export class AfterReservationComponent implements OnInit {
@Input()

hoteli: Hotel[]=[];
rentACar: RentACar[]=[];
rezervacija: Rezervacija;
  constructor(private router: Router,
              private route: ActivatedRoute,
              private letService: LetService,
              private userService: UserService) { }

  ngOnInit() {

    this.route.params.subscribe( params =>  { const id = params['grad']; const id1= params['drzava']; const idR=params['rezId'];
console.log("dobijeno");
console.log(id);
console.log(id1);
console.log(idR);
      if (id && id1) {
        this.letService.getHoteliLokacija(id, id1).then(pom=>{
          console.log("Hoteli");
          console.log(pom);
          this.hoteli=pom;
        })

        this.letService.getRentACarLokacije(id, id1).then(pom=>{
          console.log("rent a car");
          console.log(pom);
          this.rentACar=pom;
        })

        this.letService.getRez(idR).then(pom=>{
          console.log("rezervacija");
          console.log(pom);
          this.rezervacija=pom;
        })
      }
    });
  }

  zavrsiRez(){
    window.alert("You reservation is created! ");
    this.userService.sendMail(this.rezervacija).then(pom=>{
      console.log(pom);
    })
    this.router.navigateByUrl('/travel');
  }

  sledeciKorakH(id : number){
    console.log("hotel");
    console.log(id);
    this.letService.reserveHotel(id, this.rezervacija).then(pom=>{
      console.log("rez hotel");
      console.log(pom);

    })
  }
}
