import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { FilijalaService } from '../services/filijala.service';
import { Filijala } from '../model';

@Component({
  selector: 'app-filijala',
  templateUrl: './filijala.component.html',
  styleUrls: ['./filijala.component.css'],
  providers: [FilijalaService]
})

export class FilijalaComponent implements OnInit {

  @Input() filijala: Filijala;

  constructor(
    private filijalaService: FilijalaService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
  }
}
