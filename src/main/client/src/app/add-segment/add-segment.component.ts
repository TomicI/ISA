import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AviokompanijaService} from "../aviokompanija/aviokompanija.service";
import {KategorijaSedista, Segment, KonfiguracijaLeta} from "../model";

@Component({
  selector: 'app-add-segment',
  templateUrl: './add-segment.component.html',
  styleUrls: ['./add-segment.component.css'],
  providers: [AviokompanijaService]
})
export class AddSegmentComponent implements OnInit {
  @Input() id: number;
  formSeg: FormGroup;
  segment: Segment;

  kateg: KategorijaSedista[]=[];

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private aviokompanijaService: AviokompanijaService) { }

  ngOnInit() {
    this.segment=new Segment();
    this.segment.kategorija=new KategorijaSedista();
    this.segment.konfiguracija=new KonfiguracijaLeta();
    this.route.params.subscribe
    ( params => {
      this.id = params['id'];
      console.log("id " + this.id);
      this.aviokompanijaService.getKonfiguracija(this.id).then(pom=>
        { console.log("konfiguracija");
         console.log(pom);
        this.segment.konfiguracija=pom;}
      )
    })
    this.aviokompanijaService.getKategorije().then(pom=>{this.kateg=pom;})


    this.formSeg=this.formBuilder.group({
      duzina: [''],
      sirina: [''],
      kategorija: ['']
    })

  }

  onSubmit(){


    this.segment.duzina=this.formSeg.value.duzina;
    this.segment.sirina=this.formSeg.value.sirina;

    console.log("pre");
    console.log(this.segment);

    this.aviokompanijaService.addSegment(this.id, this.segment).then(pom=>
      {
        console.log(this.segment);
        this.router.navigateByUrl('konfig-list/'+this.segment.konfiguracija.aviokompanija.id);
      }
    )
  }

  onChange(value: any){
    this.aviokompanijaService.getKategorija(value).then(pom=>{ console.log(pom); this.segment.kategorija=pom;})
  }

}
