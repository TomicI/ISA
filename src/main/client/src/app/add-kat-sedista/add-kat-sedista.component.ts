import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";
import {KategorijaSedista, Aviokompanija} from "../model";

@Component({
  selector: 'app-add-kat-sedista',
  templateUrl: './add-kat-sedista.component.html',
  styleUrls: ['./add-kat-sedista.component.css'],
  providers: [AviokompanijaService]
})
export class AddKatSedistaComponent implements OnInit {
  @Input() id: number;
  formKat: FormGroup;
  kategorija: KategorijaSedista;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private aviokompanijaService: AviokompanijaService
  ) { }

  ngOnInit() {
    this.kategorija=new KategorijaSedista();
    this.kategorija.aviokompanija=new Aviokompanija();
    this.route.params.subscribe
    ( params => {
      this.id = params['id'];
      console.log("id " + this.id);
      this.kategorija.aviokompanija.id=this.id;
    })

    this.formKat=this.formBuilder.group({
      naziv: [''],
      cena: ['']
    })
  }

  onSubmit(){
    this.kategorija.naziv=this.formKat.value.naziv;
    this.kategorija.cena=this.formKat.value.cena;

    this.aviokompanijaService.createKategorija(this.kategorija).then(pom=>
      {
        console.log(pom);
        this.router.navigateByUrl('konfig-list/'+this.id);
      }
    )
  }
}
