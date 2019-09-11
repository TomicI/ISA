import {Component, HostListener, Input, OnDestroy, OnInit} from '@angular/core';
import {LetService} from "../letService/let.service";
import {Invite, Karta, Putnik, Rezervacija, User} from "../model";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../services/user.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-unos-putnika',
  templateUrl: './unos-putnika.component.html',
  styleUrls: ['./unos-putnika.component.css'],
  providers: [LetService]
})
export class UnosPutnikaComponent implements OnInit, OnDestroy {
  @Input() kartaID:number; brPutika:number;
  karta: Karta;
  rezervacija: Rezervacija;
  pBrPuntika:number;
  brojSedista: number;
  unosPurnikaForm: FormGroup;
  prijatelji: User[]=[];
  invite: Invite;

  isDirty: boolean=true;


  constructor(private letService: LetService,
              private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private userService: UserService) { }

  ngOnInit() {
    this.invite=new Invite();
    this.isDirty=true;
    this.karta=new Karta();
    this.route.params.subscribe( params =>  { const id = params['kartaID']; const id1= params['brPutnika'];

      if (id) {
        this.letService.getRez(id).then(pom=>{

          this.rezervacija=pom;
          this.karta=pom.kartaDTO;
          console.log("rez");
          console.log(pom);
          this.letService.getSedistaKarta(pom.kartaDTO.id).then(pom1=>{
            this.brojSedista=pom1.length;
            console.log("broj sedista karta " + pom1.length);
          })
        })
      }

      if(id1){
        this.pBrPuntika=id1;
        console.log("broj preostalih " + id1);
      }

    });

    this.unosPurnikaForm=this.formBuilder.group({
      brojPasosa:[''],
      ime:[''],
      prezime:['']
    });

    this.userService.getFriends().then(pom=>{
      console.log(pom);
      this.prijatelji=pom;
    })
  }

  ngOnDestroy() {
    if(this.isDirty){
      this.letService.deleteRezervacija(this.karta.id).then(pom=>{
        console.log(pom);
      })
    }
  }

  @HostListener('window:beforeunload', ['$event'])
  canLeavePage($event: any): Observable<any> {
    if(this.isDirty) {
      this.letService.deleteRezervacija(this.karta.id).then(pom=>{
        this.router.navigateByUrl('/travel');
      })
      return $event;
    }
  }


  onSubmit(){
    this.letService.savePutnik(this.unosPurnikaForm.value, this.rezervacija.kartaDTO.id).then(pom=> {
        console.log("vratilo ");
        console.log(pom);
      this.isDirty=false;
      if (this.pBrPuntika - 1 > 0) {
        this.pBrPuntika = this.pBrPuntika - 1;
        this.router.navigateByUrl('unosPutnika/' + this.rezervacija.id + '/' + this.pBrPuntika);
      }else{
        window.alert("You reservation is created! ");
        this.userService.sendMail(this.rezervacija).then(pom=>{
          console.log(pom);
        })
        this.router.navigateByUrl('/home');
      }
    });
  }

  
  
  pozovi(i:number){
    this.invite.userReceive=this.prijatelji[i];
    this.invite.reservation=this.rezervacija;
    this.userService.inviteFriend(this.invite).then(pom=>{
        console.log("send invite ");
        console.log(pom);
      this.isDirty=false;
        if (this.pBrPuntika - 1 > 0) {
          this.pBrPuntika = this.pBrPuntika - 1;
          this.router.navigateByUrl('unosPutnika/' + this.rezervacija.id + '/' + this.pBrPuntika);
        }else{
          /*window.alert("You reservation is created! ");
          this.userService.sendMail(this.rezervacija).then(pom=>{
            console.log(pom);
          })*/
          this.router.navigateByUrl('afterReservation/'+ this.rezervacija.kartaDTO.let.destinacija.grad+'/'+ this.rezervacija.kartaDTO.let.destinacija.drzava+'/'+this.rezervacija.id);
        }
      }
    )

  }
}
