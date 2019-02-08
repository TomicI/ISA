import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TokenService } from '../auth/token.service';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  buttonName = 'Sign up';

  loginDiv = true;
  signupDiv = false;


 

  isLoggedIn = false;

  roles: string[] = [];

  name = 'Log in';

  username = '';

  constructor(private modalService: NgbModal, 
    private tokenStorage: TokenService, 
    private authService: AuthService,
    private router: Router) { }
  ngOnInit() {
    
    if (this.tokenStorage.getToken()) {
      
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.username = this.tokenStorage.getUsername();

      this.roles.forEach(function(x){
        console.log(x);
        if(x === 'ROLE_ADMIN_RENT' || 'ROLE_ADMIN_AVIO' || 'ROLE_ADMIN_HOTEL'){
        
        } 
      });


  
    }



  }

  clickEvent() {

    this.loginDiv = !this.loginDiv;

    if (this.loginDiv) {
      this.name = 'Log in';
      this.buttonName = 'Sign up';
    } else {
      this.name = 'Sign up';
      this.buttonName = 'Login';
    }


  }

  open(content) {
    console.log(content);
    if (!this.isLoggedIn) {
      this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
    } else {
      this.tokenStorage.signOut();
      this.reloadPage();
    }
  }

  reloadPage() {
    window.location.reload();
  }

}
