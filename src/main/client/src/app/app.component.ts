import { Component } from '@angular/core';
import { TokenService } from './auth/token.service';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  template: '<app-navigation></app-navigation><router-outlet></router-outlet>',
  styleUrls: []
})
export class AppComponent {
  title = 'client';

  constructor(private tokenStorage: TokenService,private authService: AuthService) { }

  OnInit() {

    

  }

  
}
