import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SobaRezervacijaComponent } from './soba-rezervacija.component';

describe('SobaRezervacijaComponent', () => {
  let component: SobaRezervacijaComponent;
  let fixture: ComponentFixture<SobaRezervacijaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SobaRezervacijaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SobaRezervacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
