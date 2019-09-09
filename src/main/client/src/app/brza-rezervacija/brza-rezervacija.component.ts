import {Component, Input, OnInit} from '@angular/core';
import {Karta, Let, Sediste, Segment} from "../model";
import {LetService} from "../letService/let.service";
import {ActivatedRoute, Router} from "@angular/router";
import {fabric} from "fabric/dist/fabric.min";


@Component({
  selector: 'app-brza-rezervacija',
  templateUrl: './brza-rezervacija.component.html',
  styleUrls: ['./brza-rezervacija.component.css'],
  providers: [LetService]
})
export class BrzaRezervacijaComponent implements OnInit {
  @Input() letP:number
  letL: Let;
  segmenti: Segment[]=[]
  sedista: Sediste[]=[]
  rezervisano: number[]=[];
  karta: Karta;
  price: number;

  constructor( private letService: LetService,
               private router: Router,
               private route: ActivatedRoute) { }

  ngOnInit() {
    this.karta=new Karta();
    this.price=0;
    this.route.params.subscribe
    ( params =>  { const id = params['id'];
      if (id) {
        this.letService.getLet(id).then(pom=>{
          console.log("let");
          console.log(pom);
          this.letL=pom;
          this.karta.let=new Let(pom.id, pom.aerodrom, pom.destinacija, pom.brojSedista, pom.vremePolaska, pom.vremeDolaska, pom.presedanja, pom.brojPresedanja, pom.vremePutovanja, pom.duzinaPutovanja, pom.prosecnaOcena, pom.konfiguracijaLeta, pom.opis, pom.vrstaLeta);
          console.log("konf");
          console.log(this.karta.let.konfiguracijaLeta);

          this.letService.getSedista( this.letL.id).then(pom => {
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
    var letJ = this.letL;
    var letServiceJ = this.letService;
    var sediste ;
    this.rezervisano.push(0);
    var rez=this.rezervisano;

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
          if(rez[rez.length-1]!=0){
            sediste.set('fill', 'green');
            pomocna[sediste.brojSedista].zauzeto = false;
          }
          e.target.set('fill', 'red');
          pomocna[e.target.brojSedista].zauzeto = true;
          console.log("SEDISTE " + pomocna[e.target.brojSedista].id);
          rez.push(pomocna[e.target.brojSedista].id);
          sediste=e.target;
          //cena=cena+pomocna[e.target.brojSedista].segment.kategorija.cena;
          /*  letJ.sedista = pomocna;
            letServiceJ.zauzmiSediste(letJ);
            karta.sediste = pomocna[e.target.brojSedista];
            letServiceJ.updateKarta(karta);*/
        }
      }
      canvas.renderAll();
    });
    this.rezervisano=rez;
  }

  sledeciKorak(){
    console.log("price");
    console.log(this.price);
    console.log("rez");
    console.log(this.rezervisano);
    if(this.rezervisano[this.rezervisano.length-1]!=0 && this.price>0) {
      this.letService.brzaRezervacija(this.rezervisano[this.rezervisano.length-1], this.price).then(pom => {
        console.log("vraceno");
        console.log(pom);
        window.alert("Your reservation is create!");
        location.reload();
      })
    } else {
      window.alert("You have to write price and than select one seat!");
      location.reload();
    }
  }

}
