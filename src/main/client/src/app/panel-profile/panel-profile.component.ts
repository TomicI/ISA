import {Component, Input, OnInit} from '@angular/core';
import { User} from "../model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-panel-profile',
  templateUrl: './panel-profile.component.html',
  styleUrls: ['./panel-profile.component.css']
})
export class PanelProfileComponent implements OnInit {
  user:User;
  pom: any;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user=new User("", "", "", "", "", "", "", false, new Date(), null);
    this.userService.getUser().then((response: any) => {
      this.user = response;
      console.log(this.user); // Log here instead of outside the promise
    })

  }



}
