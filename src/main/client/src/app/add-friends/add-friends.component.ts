import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {User, Invite} from "../model";

@Component({
  selector: 'app-add-friends',
  templateUrl: './add-friends.component.html',
  styleUrls: ['./add-friends.component.css']
})
export class AddFriendsComponent implements OnInit {
  pretraga:string;
  lista: User[]=[];
  p: number=0;
  invite: Invite;
  p2: string;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.pretraga="";
    this.p=0;
    this.p2="";
    this.invite=new Invite();
  }
  pretrazi(){
    console.log(this.pretraga);
    this.p=1;
    this.p2=this.pretraga;
    this.userService.searchFriends(this.pretraga).then(pom=>{
      console.log("unutra");
      console.log(pom);
      this.lista=pom;

    });
  }

  dodaj(i: number){
    console.log(this.lista[i]);

    this.invite.userReceive=this.lista[i];

    this.userService.sendFriendrequest(this.invite).then(pom=>{
        console.log("send fr ");
        console.log(pom);
      }
    )

  }
}
