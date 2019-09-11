import { Component, OnInit } from '@angular/core';
import {Invite, Rezervacija} from "../model";
import {UserService} from "../services/user.service";
import {ReservationService} from "../services/reservation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-invite',
  templateUrl: './invite.component.html',
  styleUrls: ['./invite.component.css']
})
export class InviteComponent implements OnInit {

  params:any;

  constructor(private userService: UserService,
              private resService: ReservationService,
              private router: Router) { }

  listaI: Invite[]=[];

  reservationsInvite:Rezervacija[];

  ngOnInit() {

    this.userService.inviteRequests().then(pom=>{
      this.listaI=pom;
    });

    this.resService.getUserReservationInv().subscribe(data=>{
      console.log(data);
      this.reservationsInvite = data;

    });

  }

  resDetail(res){
    this.params = {
      'res':res.id
    };

    this.router.navigate(['homeReg/reservations/details'], { queryParams: this.params });
  }


  prihvatiI(i: number){
    console.log("invite id "+ this.listaI[i].id);
    this.userService.aRequestI(this.listaI[i].id).then(pom=>{
      console.log("Prihvati");
      console.log(pom);
      this.ngOnInit();
    });
  }

  neI(i: number){
    this.userService.eRequestI(this.listaI[i]).then(pom=>{
      console.log(pom);
      this.ngOnInit();
    });
  }


}
