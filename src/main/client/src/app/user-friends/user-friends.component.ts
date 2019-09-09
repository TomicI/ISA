import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Invite, User} from "../model";

@Component({
  selector: 'app-user-friends',
  templateUrl: './user-friends.component.html',
  styleUrls: ['./user-friends.component.css'],
  providers: [UserService]
})
export class UserFriendsComponent implements OnInit {
  lista: Invite[]=[];
  listaI: Invite[]=[];
  listaF: User[]=[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.friendRequests().then(pom=>{
      console.log(pom);
      this.lista=pom;
    })

    this.userService.inviteRequests().then(pom=>{
      console.log(pom);
      this.listaI=pom;
    })

    this.userService.getFriends().then(pom=>{
      console.log(pom);
      this.listaF=pom;
    })
  }

  prihvati(i: number){
    this.userService.aRequest(this.lista[i]).then(pom=>{
      console.log(pom);
      location.reload();
    })
  }

  ne(i: number){
    this.userService.eRequest(this.lista[i]).then(pom=>{
      console.log(pom);
      location.reload();
    })
  }

  ukloni(i:number){
    this.userService.deleteFriend(this.listaF[i]).then(pom=>{
      console.log(pom);
      location.reload();
    })
  }

  prihvatiI(i: number){
    console.log("invite id "+ this.listaI[i].id);
    this.userService.aRequestI(this.listaI[i].id).then(pom=>{
      console.log("Prihvati");
      console.log(pom);
      location.reload();
    })
  }

  neI(i: number){
    this.userService.eRequestI(this.listaI[i]).then(pom=>{
      console.log(pom);
      location.reload();
    })
  }
}
