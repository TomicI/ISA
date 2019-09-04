import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDealReservationComponent } from './new-deal-reservation.component';

describe('NewDealReservationComponent', () => {
  let component: NewDealReservationComponent;
  let fixture: ComponentFixture<NewDealReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewDealReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewDealReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
