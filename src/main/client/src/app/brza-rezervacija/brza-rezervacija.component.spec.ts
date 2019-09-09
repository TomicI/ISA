import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrzaRezervacijaComponent } from './brza-rezervacija.component';

describe('BrzaRezervacijaComponent', () => {
  let component: BrzaRezervacijaComponent;
  let fixture: ComponentFixture<BrzaRezervacijaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrzaRezervacijaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrzaRezervacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
