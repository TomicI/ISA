import {Component, HostListener, Input, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {Karta, Let, Sediste, Segment} from "../model";
import {LetService} from "../letService/let.service";
import {fabric } from 'fabric/dist/fabric.min.js';
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-karta',
  templateUrl: './karta.component.html',
  styleUrls: ['./karta.component.css'],
  providers: [LetService]
})
export class KartaComponent implements OnInit{
  @Input() letP:number




  let: Let;
  segmenti: Segment[]=[]
  sedista: Sediste[]=[]
  rezervisana: number[]=[]
  karta: Karta;
  price: number;


  constructor( private letService: LetService,
               private router: Router,
               private route: ActivatedRoute,
               private userService: UserService) { }

  ngOnInit() {
    this.karta=new Karta();
    this.price=0;
    this.route.params.subscribe
    ( params =>  { const id = params['id'];
      if (id) {
        this.letService.getLet(id).then(pom=>{
          console.log("let");
          console.log(pom);
          this.karta.let=new Let(pom.id, pom.aerodrom, pom.destinacija, pom.brojSedista, pom.vremePolaska, pom.vremeDolaska, pom.presedanja, pom.brojPresedanja, pom.vremePutovanja, pom.duzinaPutovanja, pom.prosecnaOcena, pom.konfiguracijaLeta, pom.opis, pom.vrstaLeta);
          console.log("konf");
          console.log(this.karta.let.konfiguracijaLeta);

            this.letService.getSedista( this.karta.let.id).then(pom => {
              console.log("sedista");
              console.log(pom);
              this.sedista = pom;
              this.canDr(this.sedista);
            });





        })
      }})

      this.letService.getAllSegmente().then(pom=> {

        console.log("segmeniti ");
        console.log(pom);
        this.segmenti = pom;
      })
  }



  canDr(sedista: Sediste[]) {


    var canvas = new fabric.Canvas('mycanvas', {
      selectionBorderColor: 'red',
      selectionLineWidth: 1,
      selection: false,
      controlsAboveOverlay: true,
      centeredScaling: true
    });

    var pomocna=this.sedista;
    var letJ = this.let;
    var letServiceJ = this.letService;
    var karta = this.karta;
    var rez=this.rezervisana;
    var cena=this.price;

    for (var k = 0; k < sedista.length; k++) {
      console.log("ID sedista " + this.sedista[k].id);
      var object = new fabric.Rect({
        width: 30,
        height: 30,
        opacity: 1,
        top: 30 + this.sedista[k].kolona * 50,
        left: 10 + this.sedista[k].red * 50 + this.sedista[k].segment.redniBroj*((10 + this.sedista[k].segment.sirina * 50) + 5),
        brojSedista: k
      });
      // canvas.selection = false; // disable group selection
      console.log(this.sedista[k].zauzeto);
      if (this.sedista[k].zauzeto) {
        object.set('fill', 'red');
      } else {
        object.set('fill', 'green');
      }
      object.set('selectable', false);
      canvas.add(object);
    }
   /* canvas.on('mouse:over', function(e) {
      if(e.target)
        e.target.segment.kategorija.cena;
      canvas.renderAll();
    });*/
    canvas.on('mouse:down', function (e) {
      if (e.target) {
        if (pomocna[e.target.brojSedista].zauzeto) {
          window.alert("This seat is already reserved!");
        } else {
          e.target.set('fill', 'red');
          pomocna[e.target.brojSedista].zauzeto = true;
          console.log("SEDISTE " + pomocna[e.target.brojSedista].id);
          rez.push(pomocna[e.target.brojSedista].id);
          //cena=cena+pomocna[e.target.brojSedista].segment.kategorija.cena;
        /*  letJ.sedista = pomocna;
          letServiceJ.zauzmiSediste(letJ);
          karta.sediste = pomocna[e.target.brojSedista];
          letServiceJ.updateKarta(karta);*/
        }
      }
      canvas.renderAll();
    });

    this.price=cena;
    this.rezervisana=rez;
  }

  sledeciKorak(){
    console.log("pre");
    console.log(this.rezervisana);
    this.letService.saveKarta(this.rezervisana).then(pom=>
    {
      console.log("vratilo");
      console.log(pom);
      if(this.rezervisana.length>1) {
        var num = this.rezervisana.length - 1;
        this.router.navigateByUrl('unosPutnika/' + pom.id + '/' + num);
      }else{
        window.alert("You reservation is created! ");
        this.userService.sendMail(pom).then(pom=>{
          console.log(pom);
        })
        this.router.navigateByUrl('/home');
      }
    })
  }
}
