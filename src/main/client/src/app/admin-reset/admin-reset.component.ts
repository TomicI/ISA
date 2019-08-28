import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../services/user.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AuthService} from "../auth/auth.service";
import {TokenService} from "../auth/token.service";
import {NewPass} from "../model";
import {ConfirmPasswordValidator} from "../sign-up-modal/confirm-pass.validator";

@Component({
  selector: 'app-admin-reset',
  templateUrl: './admin-reset.component.html',
  styleUrls: ['./admin-reset.component.css']
})
export class AdminResetComponent implements OnInit {

  passFormGroup: FormGroup;

  errorSub:any;

  submitError = false;

  constructor(private userService: UserService,
              private fb: FormBuilder,
              private modalService: NgbModal,
              private authService: AuthService,
              private tokenStorage: TokenService
  ) {
  }

  ngOnInit() {

    this.passFormGroup = this.fb.group({
        password: [''],
        passwordConfirm: ['']
      },{
      validator: ConfirmPasswordValidator.validate.bind(this)
      }
    )
  }

  changePass() {

    const newPass = new NewPass(
      null,
      this.passFormGroup.get('password').value
    );

    this.authService.changeAdminPass(newPass).catch(error => {
      this.errorSub = error.error.error;

      this.submitError = true;
      this.passFormGroup.reset();
    });

  }


}
