import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { TokenService } from '../auth/token.service';

import { SignupComponent } from '../signup/signup.component';
import { Template } from '@angular/compiler/src/render3/r3_ast';
import { UserLogin } from '../model';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  buttonName = 'Log in';
  form: any = {};
  loginDiv = true;
  signupDiv = false;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: UserLogin;

  name = 'Log in';

  constructor(private authService: AuthService, private tokenStorage: TokenService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  onSubmit() {
    console.log(this.form);

    this.loginInfo = new UserLogin(
      this.form.username,
      this.form.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
        this.name = 'Log out';
      }
    );
  }

  

  reloadPage() {
    window.location.reload();
  }
}
