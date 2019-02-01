import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';
import { Filijala } from '../model';
import { RentacarService } from '../services/rentacar.service';



@Component({
  selector: 'app-filijala-list',
  templateUrl: './filijala-list.component.html',
  styleUrls: ['./filijala-list.component.css'],
  providers: [RentacarService],
})
export class FilijalaListComponent implements OnInit {

  @Input() id: number;

  filijale: Observable<any>;

  constructor(
    private rentACarService: RentacarService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.getFilijale();
  }

  getFilijale() {
    this.filijale = this.rentACarService.getAllFilijale(this.id);
  }

}
