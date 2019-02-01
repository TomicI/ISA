import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { RentacarService } from '../services/rentacar.service';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.css'],
  providers: [RentacarService],
})
export class PanelComponent implements OnInit {


  aerop: string;
  aerod: string;

  constructor() { }

  ngOnInit() {
  }


}
