import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { TokenService } from '../auth/token.service';
import { LetService } from '../letService/let.service';
import { AerodromSService } from '../aerodrom-s/aerodrom-s.service';
import { Let, Aerodrom, Karta, User } from '../model';
import {Observable } from 'rxjs';
import {FormControl} from '@angular/forms';
import {map, startWith} from 'rxjs/operators';
import {MatAutocompleteModule, MatInputModule} from '@angular/material';
import { LetComponent } from '../let/let.component';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [LetService, AerodromSService, UserService]
})
export class HomeComponent implements OnInit {
  info: any;
  searchFormGroup: FormGroup;
  submitted = false;
  pretraga: Let
  aerodromi: Observable<Aerodrom[]>
  rezultat: Promise<Let[]>
  options: String[]=[];
  presedanja: Aerodrom[]=[];
  prikazi=0;
  filteredOptions: Observable<String[]>;
  myControl = new FormControl();
  vremeP: String;
  vremeS: String;
  tabela= false;
  karta: Karta;
  user: User;
  /*pretraga= new Object({
    datumP: Date,
    datumS: Date,
    vremeP: String,
    vremeS: String
  })*/

  constructor(private token: TokenService,
    private formBuilder: FormBuilder,
    private letService: LetService,
    private aerdormService: AerodromSService,
    private router: Router,
    private userService: UserService) { }
 
  ngOnInit() {
    this.userService.getUser().then(data => {
      this.user = data;

    });
    this.karta=new Karta;
    this.aerodromi=this.aerdormService.getAllAerodromi();
    this.aerodromi.forEach(element => {
      for(let i=0; i<element.length; i++){
        this.options.push(element[i].nazivAerodroma);
      }
    });
    this.pretraga=new Let();
    this.vremeP='';
    this.vremeS='';
    this.filteredOptions = this.myControl.valueChanges
    .pipe(
      startWith(''),
      map(value => this._filter(value))
    );

    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };

    this.searchFormGroup=this.formBuilder.group({
      datumP: [''],
      datumS: [''],
      vremeP:[''],
      vremeS:['']
    })

  }

  onSubmit(){
    this.submitted=true;
    console.log(this.searchFormGroup.value);
    console.log(this.vremeP);
    console.log(this.vremeS);
    this.pretraga=this.searchFormGroup.value;
    this.pretraga.vremeP=this.vremeP;
    this.pretraga.vremeS=this.vremeS;
    this.rezultat=this.letService.pretraga(this.pretraga);
    this.tabela=true;
    
  }
 
  logout() {
    this.token.signOut();
    window.location.reload();
  }

  private _filter(value: string): String[] {
    if(this.options){
      const filterValue = value.toLowerCase();

      return this.options.filter(option => option.toLowerCase().includes(filterValue));
    }
  }
  sledeciKorak(l: Let)
  {
    console.log("sledeci korak             " + l.id + "  " + l.datumP);
    this.karta.let=l;
    this.karta.user=this.user;
    var pom;
    var pom1;
    this.letService.saveKarta(this.karta).then(value=>{
      pom1=JSON.stringify(value);
      pom=JSON.parse(pom1);
      this.router.navigateByUrl('karta/'+pom.id);
    })
    
   
  }
}