import { Component, Input,  ViewChild, ElementRef, NgZone, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router, Params } from '@angular/router';

import { AviokompanijaService } from '../aviokompanija/aviokompanija.service';
import {Aviokompanija, Lokacija} from '../model';
import { AviokomListComponent } from '../aviokom-list/aviokom-list.component';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { variable } from '@angular/compiler/src/output/output_ast';
import * as mapboxgl  from 'mapbox-gl';
//import  {geo} from 'mapbox-geocoding';
import 'mapbox-gl/dist/mapbox-gl.css';  
import {FeatureCollection} from 'mapbox-gl';
import {FormBuilder, FormGroup} from "@angular/forms";
/*
import { MapsAPILoader, AgmMap } from '@agm/core';
import { GoogleMapsAPIWrapper } from '@agm/core/services';

declare var google: any;
 
interface Marker {
  latitude: number;
  longitude: number;
  label?: string;
  draggable: boolean;
}
 
interface Location {
  latitude: number;
  longitude: number;
  viewport?: Object;
  zoom: number;
  address_level_1?:string;
  address_level_2?: string;
  address_country?: string;
  address_zip?: string;
  address_state?: string;
  marker?: Marker;
}*/


@Component({
  selector: 'app-avio-edit',
  templateUrl: './avio-edit.component.html',
  styleUrls: ['./avio-edit.component.css'],
  providers: [AviokompanijaService, AviokomListComponent]
})
export class AvioEditComponent implements OnInit {
	aviokompanija: Aviokompanija;
  regFormA: FormGroup;
  submitted = false;
  /*geocoder:any;
  latitude= 45.2671352;
  longitude= 19.83354959999997;
  public location:Location = {
  latitude: 45.2671352,
  longitude: 19.83354959999997,
    marker: {
      latitude: 45.2671352,
      longitude: 19.83354959999997,
      draggable: true
    },
    zoom: 5
  };*/
     
  /*
  map: mapboxgl.Map;  
  popup: any; 
  lat=37.75;
  lng=-122.41;

  pom:FeatureCollection;
  markers:any;
  adresa: any;*/
  constructor(private route: ActivatedRoute,
              private formBuilder: FormBuilder ,
              private router: Router,
              private aviokompanijaService: AviokompanijaService,
              private aviokompanije: AviokomListComponent,
              
            //  public mapsApiLoader: MapsAPILoader,
            //  private zone: NgZone,
             // private wrapper: GoogleMapsAPIWrapper
             ) {/*this.mapsApiLoader = mapsApiLoader;
              this.zone = zone;
              this.wrapper = wrapper;
              this.mapsApiLoader.load().then(() => {
                this.geocoder = new google.maps.Geocoder(); });*/
                /* this.aviokompanijaService.getLL('Beograd').subscribe(
      (response) =>{ 
        this.pom=response;
        console.log("odgovor");
      console.log(response)});*/
                
              }



 ngOnInit() {
   this.aviokompanija=new Aviokompanija();
   this.regFormA=this.formBuilder.group({
     naziv: [''],
     adresa:[''],
     grad: [''],
     drzava: [''],
     opis:['']
   })

   this.aviokompanijaService.getAviokompanijaAdmin().then(pom=>{
     console.log("avkom");
     console.log(pom);
     this.aviokompanija=pom;
     this.regFormA=this.formBuilder.group({
       naziv: pom.naziv,
       adresa: pom.lokacijaDTO.adresa,
       grad: pom.lokacijaDTO.grad,
       drzava: pom.lokacijaDTO.drzava,
       opis: pom.opis
     })

   })
    //mapboxgl.accessToken = 'pk.eyJ1IjoiaXYzIiwiYSI6ImNqcmw4dTFkZzA1a2E0M280cmN5ZzB2azcifQ.onqhM8uoPAXAYdE9JRJX0g';
   // geo.setAccessToken('pk.eyJ1IjoiaXYzIiwiYSI6ImNqcmw4dTFkZzA1a2E0M280cmN5ZzB2azcifQ.onqhM8uoPAXAYdE9JRJX0g');
  // this.aviokompanija=new Aviokompanija();
  //  this.route.params.subscribe
  //     ( params =>  { const id = params['id'];
  //     if (id) {
  //       console.log(`Avikompanija with id '${id}' `);
  //       this.aviokompanijaService.getAviokompanija(id).subscribe((aviokompanija: any) => {
  //         if (aviokompanija) {
  //           this.aviokompanija = aviokompanija;
  //           console.log(`Pronadjeno '${aviokompanija.naziv}' `);
            
  //           console.log('Adresa  '+ this.aviokompanija.adresa );
  //           //this.findLocation(this.aviokompanija.adresa);
  //           this.ucitajC();
  //         } else {
  //           console.log(`Avikompanija with id '${id}' not found `);
  //         }

  //       });
  //     }
  //   });
    //console.log(geo.geocode('mapbox.places', 'Jevrjska, Novi Sad'));
   

  }

  onSubmit(){
    this.submitted=true;
    this.aviokompanija.naziv=this.regFormA.value.naziv;
    this.aviokompanija.lokacijaDTO=new Lokacija();
    this.aviokompanija.lokacijaDTO.adresa=this.regFormA.value.adresa;
    this.aviokompanija.lokacijaDTO.grad=this.regFormA.value.grad;
    this.aviokompanija.lokacijaDTO.drzava=this.regFormA.value.drzava;
    this.aviokompanija.opis=this.regFormA.value.opis;

    this.aviokompanijaService.updateAviokompanija(this.aviokompanija).then(pom=>
    {
      console.log("vratilo");
      console.log(pom);
      this.router.navigateByUrl('aviokompanijaProfil');
    });
  }
/*
  ngAfterViewInit(){
    if(this.pom){
    this.map = new mapboxgl.Map({
      container: 'divMapa',
      style: 'mapbox://styles/mapbox/streets-v11',
      zoom: 13, 
      center: [this.lng, this.lat]
    });

    this.popup = new mapboxgl.Popup()
    .setLngLat(this.pom)
    .setHTML('<h3>'+this.aviokompanija.naziv+'</h3>')
    .setLngLat(this.pom)
    .addTo(this.map);
   }
  }

  ucitajC(){
    console.log("adresa UC " + this.aviokompanija.adresa);
    if(this.aviokompanija.adresa){
      this.adresa=this.aviokompanija.adresa.replace(/ /g,"%20");
      this.adresa=this.adresa.replace(/,/g,"");
      this.aviokompanijaService.getLL(this.aviokompanija.adresa).subscribe((response) =>{ 
        this.pom=JSON.stringify(response);
        console.log("odgovor");
        
        this.pom=JSON.parse(this.pom);
        console.log("Centar");
        console.log(this.pom.features[0].center);
        this.lat=this.pom.features[0].center[1];
        this.lng=this.pom.features[0].center[0];
        this.pom=this.pom.features[0].center;
        if(this.pom)
          this.ngAfterViewInit();
    
    })}
    console.log("posle");
      console.log(this.pom);
  }
 /* public ngAfterViewInit() {
    
    
    let defaultLayers = this.platform.createDefaultLayers();
    let map = new H.Map(
        this.mapElement.nativeElement,
        defaultLayers.normal.map,
        {
            zoom: 10,
            center: { lat: 37.7397, lng: -121.4252 }
        }
    );
  }*/


/*
  deleteAk(id){
    this.aviokompanijaService.deleteAviokompanija(id).then(pom=>{
      console.log('Uspesno obrisano! ');
      this.router.navigateByUrl('aviokom-list');

    })

  }
  updateAk(id){
    console.log("Za update "  )
    this.router.navigateByUrl('form-akupdate/'+id);
  }
  /*
  findLocation(address) {
    if (!this.geocoder) this.geocoder = new google.maps.Geocoder()
    this.geocoder.geocode({
      'address': address
    }, (results, status) => {
      console.log(results);
      if (status == google.maps.GeocoderStatus.OK) {
        for (var i = 0; i < results[0].address_components.length; i++) {
          let types = results[0].address_components[i].types
 
          if (types.indexOf('locality') != -1) {
            this.location.address_level_2 = results[0].address_components[i].long_name
          }
          if (types.indexOf('country') != -1) {
            this.location.address_country = results[0].address_components[i].long_name
          }
          if (types.indexOf('postal_code') != -1) {
            this.location.address_zip = results[0].address_components[i].long_name
          }
          if (types.indexOf('administrative_area_level_1') != -1) {
            this.location.address_state = results[0].address_components[i].long_name
          }
        }
 
        if (results[0].geometry.location) {
          this.location.latitude = results[0].geometry.location.lat();
          this.location.longitude = results[0].geometry.location.lng();
          this.location.marker.latitude = results[0].geometry.location.lat();
          this.location.marker.longitude = results[0].geometry.location.lng();
          this.location.marker.draggable = true;
          this.location.viewport = results[0].geometry.viewport;


        }
        console.log(" LAT " + this.location.latitude);
        console.log(" LNG " + this.location.longitude);

        console.log(" LAT " + this.location.marker.latitude );
        console.log(" LNG " + this.location.marker.longitude);
        this.map.triggerResize()
      } else {
        alert("Sorry, this search produced no results.");
      }
    })
  }*/

}
