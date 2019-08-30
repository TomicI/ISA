import {Component} from '@angular/core';
import {TokenService} from './auth/token.service';
import {AuthService} from './auth/auth.service';
import {Event, NavigationEnd, NavigationError, NavigationStart, Router} from "@angular/router";
import {UserService} from "./services/user.service";

@Component({
  selector: 'app-root',
  template: '<app-navigation></app-navigation><router-outlet></router-outlet>',
  styleUrls: []
})
export class AppComponent {
  title = 'client';

  roles: any[] = ['ROLE_ADMIN_RENT', 'ROLE_ADMIN_AVIO', 'ROLE_ADMIN_HOTEL'];

  constructor(private tokenStorage: TokenService, private authService: AuthService, private router: Router, private userService: UserService) {

    this.router.events.subscribe((event: Event) => {
      if (event instanceof NavigationStart) {
        // Show loading indicator
        console.log("PROMENILO SESESE")
        console.log(event.url)


        const token = this.tokenStorage.getToken();
        if (token != null) {
          const authorities = this.tokenStorage.getAuthorities();
          for (let role of this.roles) {
            if (authorities.includes(role)) {

              this.userService.checkReset().subscribe(data => {
                if (event.url == "/resetPassword") {
                  this.router.navigate(['home']);
                }
              }, error => {
                if (event.url != "/resetPassword") {
                  this.router.navigate(['resetPassword']);
                }
              });

            }
          }
        }


      }

      if (event instanceof NavigationEnd) {
        // Hide loading indicator
      }

      if (event instanceof NavigationError) {
        // Hide loading indicator

        // Present error to user
        console.log(event.error);
      }
    });

  }

  OnInit() {


  }


}
