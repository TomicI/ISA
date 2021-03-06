import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { User } from '../model';
import { ConfirmPasswordValidator } from './confirm-pass.validator';


@Component({
  selector: 'app-sign-up-modal',
  templateUrl: './sign-up-modal.component.html',
  styleUrls: ['./sign-up-modal.component.css']
})
export class SignUpModalComponent implements OnInit {

  signUpForm: FormGroup;
  user: User;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  
  submitted = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit() {

    this.signUpForm = this.formBuilder.group({
      firstName: new FormControl('', [Validators.minLength(3), Validators.required]),
      lastName: [''],
      city:[''],
      passport:[''],
      phone:[''],
      username: [''],
      email: [''],
      password: [''],
      passwordConfirm: ['']
    }, {
        validator: ConfirmPasswordValidator.validate.bind(this)

      });

  }
  onSubmit() {
   
    this.authService.signUp(this.signUpForm.value).subscribe(
      data => {
        this.isSignedUp = true;
        this.isSignUpFailed = false
        alert('User registered!');
      }, error => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }

    );


  }

}
