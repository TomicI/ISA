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

  onChangeName(value){
    if (value=='all'){
      this.ngOnInit();
    }else if (value=='asc'){
      this.listaF.sort(((a, b) => a.firstName.localeCompare(b.firstName)));

    }else if (value=='des'){
      this.listaF.sort(((a, b) => b.firstName.localeCompare(a.firstName)));
    }else if (value=='ascl'){
      this.listaF.sort(((a, b) => a.lastName.localeCompare(b.lastName)));

    }else if (value=='desl'){
      this.listaF.sort(((a, b) => b.lastName.localeCompare(a.lastName)));
    }

  }


}
