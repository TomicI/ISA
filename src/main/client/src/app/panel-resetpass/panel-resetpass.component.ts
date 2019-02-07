import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { ConfirmPasswordValidator } from '../sign-up-modal/confirm-pass.validator';

@Component({
  selector: 'app-panel-resetpass',
  templateUrl: './panel-resetpass.component.html',
  styleUrls: ['./panel-resetpass.component.css']
})
export class PanelResetpassComponent implements OnInit {

  resetForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit() {

    this.resetForm = this.formBuilder.group({
      password: [''],
      passwordConfirm: ['']
    }, {
      validator: ConfirmPasswordValidator.validate.bind(this)
    });

  }

}
