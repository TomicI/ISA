import { Component, OnInit , Input} from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { RentacarService } from '../services/rentacar.service';
import { RentACar } from '../model';



@Component({
  selector: 'app-rentacar',
  templateUrl: './rentacar.component.html',
  styleUrls: ['./rentacar.component.css'],
  providers: [RentacarService],
})
export class RentacarComponent implements OnInit {

  @Input() rentacar: RentACar;

  constructor(
    private rentACarService: RentacarService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
    this.route.params.subscribe
      ( params =>  { const id = params['id'];
      if (id) {
        this.rentACarService.getRentACar(id).subscribe((rentacar: any) => {
          if (rentacar) {
            this.rentacar = rentacar;
          } else {
            console.log(`RentACar with id '${id}' not found `);
          }

        });
      }
    });
  }



}
