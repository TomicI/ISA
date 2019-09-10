import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrzeRezervacijeListaComponent } from './brze-rezervacije-lista.component';

describe('BrzeRezervacijeListaComponent', () => {
  let component: BrzeRezervacijeListaComponent;
  let fixture: ComponentFixture<BrzeRezervacijeListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrzeRezervacijeListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrzeRezervacijeListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
