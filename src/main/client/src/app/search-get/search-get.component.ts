import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RentacarService } from '../services/rentacar.service';

@Component({
  selector: 'app-search-get',
  templateUrl: './search-get.component.html',
  styleUrls: ['./search-get.component.css'],
  providers: [RentacarService]
})
export class SearchGetComponent implements OnInit {

  params: any = {};

  constructor(private route: ActivatedRoute,private rentService: RentacarService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.params = params;
    });

    this.rentService.search(this.params).toPromise().then(data=>{

    });

  }

}
