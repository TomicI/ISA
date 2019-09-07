import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelPrikazComponent } from './hotel-prikaz.component';

describe('HotelPrikazComponent', () => {
  let component: HotelPrikazComponent;
  let fixture: ComponentFixture<HotelPrikazComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelPrikazComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelPrikazComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
