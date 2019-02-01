import { Component, OnInit, Input } from '@angular/core';

import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  @Input() temp;


  form: any = {};
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit() {

  }

  open(content) {



  }


}


