import { Component, OnInit, enableProdMode, Input } from '@angular/core';
import {fabric } from 'fabric/dist/fabric.min.js';
import {Let, Sediste, Karta} from '../model';
import {LetService} from '../letService/let.service';
import { Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { FunctionCall } from '@angular/compiler';


enableProdMode();
@Component({
  selector: 'app-karta',
  templateUrl: './karta.component.html',
  styleUrls: ['./karta.component.css'],
  providers:[LetService]
})


export class KartaComponent implements OnInit {
  @Input() letP:number
  let: Let
  sedista: Sediste[]=[]
  karta: Karta;
  constructor(
    private letService: LetService,
    private router: Router,
    private route: ActivatedRoute

  ) { }
  
  ngOnInit() {
    this.let=new Let();

    this.route.params.subscribe
      ( params =>  { const id = params['id'];
      if (id) {
        this.letService.getKarta(id).subscribe( kart=>{
          
          var pom=kart;
          var pom1=JSON.stringify(kart);
          this.karta=JSON.parse(pom1);
          this.let=this.karta.let;
          if(this.let){
            this.sedista=this.let.sedista;
            this.canDr();
          }
        })
      }});
    }
    
    

      /*var object = new fabric.Rect({
        width: 100,
        height: 100,
        fill: 'blue',
        opacity: 1,
        left: 0,
        top: 0
      });
      canvas.add(object);
*/
      
  

  canDr(){
    var canvas = new fabric.Canvas('mycanvas', {
      selectionBorderColor: 'red',
      selectionLineWidth: 1,
      selection: false,
      controlsAboveOverlay: true,
      centeredScaling: true
      });

      var pomocna=this.sedista;
      var letJ=this.let;
      var letServiceJ=this.letService;
      var karta=this.karta;

      for(var k=0; k< this.sedista.length; k++){
        console.log("ID sedista "+this.sedista[k].id);
        var object = new fabric.Rect({
          width: 50,
          height: 50,
          opacity: 1,
          top: 50+this.sedista[k].kolona*100,
          left: 30+this.sedista[k].red*100+this.sedista[k].segment*((30+this.let.brojRedova*100)+10),
          brojSedista: k
        });
      // canvas.selection = false; // disable group selection
        console.log(this.sedista[k].zauzeto);
        if(this.sedista[k].zauzeto){
          object.set('fill', 'red');
        }else{
          object.set('fill', 'green');
        }
        object.set('selectable', false);
        canvas.add(object);        
      }
/*
      for (var i = this.let.brojKolona; i--; ) {
        for(var j=this.let.brojRedova;  j--;){
          //var dim = fabric.util.getRandomInt(30, 60);
          var object = new fabric.Rect({
            width: 50,
            height: 50,
            fill: 'blue',
            opacity: 1,
            top: 50+j*100,
            left: 50+i*100
          });
        // canvas.selection = false; // disable group selection
          object.set('selectable', false);
          canvas.add(object);
      }
    
  }*/

    /*canvas.on('mouse:over', function(e) {
      if(e.target)
       e.target.set('fill', 'red');
       canvas.renderAll();
    });*/

    canvas.on('mouse:down', function(e) {
      if(e.target){
        if(pomocna[e.target.brojSedista].zauzeto){
          window.alert("This seat is already reserved!");
        }else{
          e.target.set('fill', 'red');
          pomocna[e.target.brojSedista].zauzeto=true;
          console.log("SEDISTE " + pomocna[e.target.brojSedista].id);
          letJ.sedista=pomocna;
          letServiceJ.zauzmiSediste(letJ);
          karta.sediste=pomocna[e.target.brojSedista];
          letServiceJ.updateKarta(karta);
        }
      }
        canvas.renderAll();
    });

    
    
  }
  
  
}
