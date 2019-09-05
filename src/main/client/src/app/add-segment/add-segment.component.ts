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
    this.aviokompanijaService.getKategorije().then(pom=>{
      console.log("kategorije");
      console.log(pom);
      this.kateg=pom;
    })


    this.formSeg=this.formBuilder.group({
      duzina: [1],
      sirina: [1],
      kategorija: [this.kateg[0]]
    })

  }

  onSubmit(){


    this.segment.duzina=this.formSeg.value.duzina;
    this.segment.sirina=this.formSeg.value.sirina;
    if(this.formSeg.value.kategorija==null){
      console.log("null je ");
      console.log(this.kateg[0]);
      this.segment.kategorija=this.kateg[0];
    }else{
      this.segment.kategorija=this.formSeg.value.kategorija;
    }

    console.log("pre");
    console.log(this.segment);

    console.log("preF");
    console.log(this.formSeg.value);

    this.aviokompanijaService.addSegment(this.id, this.segment).then(pom=>
      {
        console.log("vratilo");
        console.log(this.segment);
        this.router.navigateByUrl('konfig-list/'+this.segment.konfiguracija.aviokompanija.id);
      }
    )
  }

  onChange(value: any){
    console.log("kategorija " + value);
    this.aviokompanijaService.getKategorija(value).then(pom=>{console.log("nadjena kategorija"); console.log(pom); this.formSeg.value.kategorija=pom;})
  }

}
