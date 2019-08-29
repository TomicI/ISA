import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Invite} from "../model";

@Component({
  selector: 'app-user-friends',
  templateUrl: './user-friends.component.html',
  styleUrls: ['./user-friends.component.css']
})
export class UserFriendsComponent implements OnInit {
  lista: Invite[]=[];
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.friendRequests().then(pom=>{
      console.log(pom);
      this.lista=pom;
    })

  }

  prihvati(i: number){

  }

  ne(i: number){

  }
}
