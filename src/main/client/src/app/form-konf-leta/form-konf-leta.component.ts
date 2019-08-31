import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";
import {KonfiguracijaLeta, Aviokompanija} from "../model";

@Component({
  selector: 'app-form-konf-leta',
  templateUrl: './form-konf-leta.component.html',
  styleUrls: ['./form-konf-leta.component.css'],
  providers: [AviokompanijaService]
})
export class FormKonfLetaComponent implements OnInit {
  @Input() id: number;

  formKonf: FormGroup;
  konfLeta: KonfiguracijaLeta;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private aviokompanijaService: AviokompanijaService
  ) { }

  ngOnInit() {

    this.konfLeta=new KonfiguracijaLeta();
    this.konfLeta.aviokompanija=new Aviokompanija();
    this.route.params.subscribe
    ( params => {
      this.id = params['id'];
      console.log("id " + this.id);

      this.konfLeta.aviokompanija.id=this.id;
    })
    this.formKonf=this.formBuilder.group({
      naziv: ['']
    })
  }

  onSubmit(){
    this.konfLeta.naziv=this.formKonf.value.naziv;
    this.aviokompanijaService.createKonfiguracija(this.id, this.konfLeta).then(pom=>
      {
        console.log(pom);
        this.router.navigateByUrl('konfig-list/'+this.id);
      }
    )
  }

}
