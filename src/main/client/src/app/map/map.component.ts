import { Component, OnInit, ViewChild, ElementRef, Input } from '@angular/core';
import * as mapboxgl from 'mapbox-gl';
import { FeatureCollection } from 'mapbox-gl';
import { MapService } from '../services/map.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  @ViewChild('divMapa') mapElement: ElementRef;
  @Input() adress = '';

  map: mapboxgl.Map;
  popup: any;
  lat = 37.75;
  lng = -122.41;

  mapInfo;
  pom: FeatureCollection;
  markers: any;
  adresa: any;

  constructor(private mapService: MapService) { }

  ngOnInit() {
    mapboxgl.accessToken = 'pk.eyJ1IjoiaXYzIiwiYSI6ImNqcmw4dTFkZzA1a2E0M280cmN5ZzB2azcifQ.onqhM8uoPAXAYdE9JRJX0g';
    this.mapService.getLL(this.adress).toPromise().then(data => {
      this.pom = JSON.stringify(data);
      this.pom = JSON.parse(this.pom);
      this.lat = this.pom.features[0].center[1];
      this.lng = this.pom.features[0].center[0];
      this.pom = this.pom.features[0].center;
      this.map = new mapboxgl.Map({
        container: this.mapElement.nativeElement,
        style: 'mapbox://styles/mapbox/streets-v11',
        zoom: 13,
        center: [this.lng, this.lat]
      });
    });

  }

}
