import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Invite, User} from "../model";

@Component({
  selector: 'app-user-friends',
  templateUrl: './user-friends.component.html',
  styleUrls: ['./user-friends.component.css']
})
export class UserFriendsComponent implements OnInit {
  lista: Invite[]=[];
  listaF: User[]=[];
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.friendRequests().then(pom=>{
      console.log(pom);
      this.lista=pom;
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
}
