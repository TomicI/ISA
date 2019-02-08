import { Component, OnInit } from '@angular/core';
import { RentacarService } from '../services/rentacar.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Vozilo } from '../model';
import { FilijalaService } from '../services/filijala.service';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css'],
  providers:[FilijalaService]
})
export class VehicleComponent implements OnInit {

  

  vozila: Observable<Vozilo[]>;

  constructor(private rentacarService: RentacarService,
    private filService: FilijalaService,
    private route: ActivatedRoute) { }

  ngOnInit() {

    this.route.queryParams.subscribe(params => {

      this.filService.getAllVozila(params.id).subscribe(data => {
          this.vozila = data;
      });


    });
  }

}
