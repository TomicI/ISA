import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelRezervisiComponent } from './hotel-rezervisi.component';

describe('HotelRezervisiComponent', () => {
  let component: HotelRezervisiComponent;
  let fixture: ComponentFixture<HotelRezervisiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelRezervisiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelRezervisiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
