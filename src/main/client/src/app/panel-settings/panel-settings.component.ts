import { Component, OnInit, ViewChild } from '@angular/core';
import { User, NewPass, RentACar } from '../model';
import { UserService } from '../services/user.service';
import { FormBuilder, FormGroup, Form } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmPasswordValidator } from '../sign-up-modal/confirm-pass.validator';
import { AuthService } from '../auth/auth.service';
import { TokenService } from '../auth/token.service';


@Component({
  selector: 'app-panel-settings',
  templateUrl: './panel-settings.component.html',
  styleUrls: ['./panel-settings.component.css']
})
export class PanelSettingsComponent implements OnInit {


  @ViewChild('content') private content;
  @ViewChild('contentRent') private contentRent;

  
  profileFormGroup: FormGroup;
  passFormGroup: FormGroup;

  user: User;

  rent: RentACar;
  rentFormGroup: FormGroup;

  openPass = false;
  userDetail = false;

  inp1 = false;
  inp2 = false;
  inp3 = false;

  name = false;
  username = false;
  email = false;

  input1 = '';
  input2 = '';
  input3 = '';

  errorSub = '';

  submitError = false;

  modalRef: any;

  constructor(private userService: UserService,
    private fb: FormBuilder,
    private modalService: NgbModal,
    private authService: AuthService,
    private tokenStorage: TokenService
  ) { }

  ngOnInit() {
    
    this.initRent();

    this.initUser();

    console.log(this.tokenStorage.getUsername());

    this.profileFormGroup = this.fb.group({
      input1: null,
      input2: null,
      input3: null
    });


  }

  async initUser() {



    await this.userService.getUser().then(data => {
      this.user = data;

    });

    

  }

  async initRent() {

    this.rent = await this.userService.getUserRentACar().toPromise();

  }

  initForm() {
    this.userDetail = true;
    this.openPass = false;
    this.submitError = false;
  }

  editRent() {

    this.rentFormGroup = this.fb.group(new RentACar(
      this.rent.id,
      this.rent.naziv,
      this.rent.opis,
      this.rent.adresa
    ));

    this.modalRef = this.modalService.open(this.contentRent);

  }

  onClicked() {

    this.initRent();
    this.modalRef.close();
  }

  editName() {

    this.initForm();

    this.modalRef = this.modalService.open(this.content);

    this.input1 = 'First name';
    this.input2 = 'Last name';

    this.profileFormGroup = this.fb.group({
      input1: this.user.firstName,
      input2: this.user.lastName,
      input3: null
    });

    this.inp1 = true;
    this.inp2 = true;
    this.inp3 = false;

    this.name = true;
    this.username = false;
    this.email = false;

  }

  editUsername() {

    this.initForm();

    this.input1 = 'Username';

    this.profileFormGroup = this.fb.group({
      input1: this.user.username,
      input2: null,
      input3: null
    });

    this.inp1 = true;
    this.inp2 = false;
    this.inp3 = false;

    this.name = false;
    this.username = true;
    this.email = false;


    this.modalRef = this.modalService.open(this.content);
  }

  editEmail() {

    this.initForm();

    this.input1 = 'Email';

    this.profileFormGroup = this.fb.group({
      input1: this.user.email,
      input2: null,
      input3: null
    });

    this.inp1 = true;
    this.inp2 = false;
    this.inp3 = false;

    this.name = false;
    this.username = false;
    this.email = true;

    this.modalRef = this.modalService.open(this.content);
  }

  editPass() {

    this.userDetail = false;
    this.openPass = true;

    this.submitError = false;
    this.resetPassField();

    this.modalRef = this.modalService.open(this.content);
  }

  async submit() {

    this.submitError = false;

    if (this.name === true) {
      console.log('NAME');

      const u = new User(
        this.profileFormGroup.get('input1').value,
        this.profileFormGroup.get('input2').value,
        null,
        null,
        null
      );

      await this.authService.changeName(u).catch(error => {
        this.errorSub = error.error.message;
        this.submitError = true;
      });

    } else if (this.username === true) {

      console.log('USERNAME');
      const u = new User(
        null,
        null,
        this.profileFormGroup.get('input1').value,
        null,
        null
      );

      await this.authService.changeUsername(u).then(data => {

        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.reloadPage();

      }).catch(error => {
        this.errorSub = error.error.message;

        this.submitError = true;
      });


    } else if (this.email === true) {
      console.log('EMAIL');
      const u = new User(
        null,
        null,
        null,
        this.profileFormGroup.get('input1').value,
        null
      );

      await this.authService.changeEmail(u).catch(error => {
        this.errorSub = error.error.message;

        this.submitError = true;
      });


    }

    console.log(this.submitError);

    if (!this.submitError) {
      this.modalRef.close();
    }

    await this.initUser();

    console.log(this.profileFormGroup.value);
  }

  changePass() {

    const newPass = new NewPass(
      this.passFormGroup.get('oldPassword').value,
      this.passFormGroup.get('password').value
    );

    this.authService.changePass(newPass).catch(error => {
      this.errorSub = 'Bad Credentials';

      this.submitError = true;

      this.resetPassField();
    });

  }



  resetPassField() {

    this.passFormGroup = this.fb.group({
      oldPassword: [''],
      password: [''],
      passwordConfirm: ['']
    }, {
        validator: ConfirmPasswordValidator.validate.bind(this)

      });

  }
  reloadPage() {
    window.location.reload();
  }


}
