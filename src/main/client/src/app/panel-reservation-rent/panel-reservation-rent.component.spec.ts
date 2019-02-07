import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelReservationRentComponent } from './panel-reservation-rent.component';

describe('PanelReservationRentComponent', () => {
  let component: PanelReservationRentComponent;
  let fixture: ComponentFixture<PanelReservationRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PanelReservationRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelReservationRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
