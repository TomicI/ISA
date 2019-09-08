import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelSobaComponent } from './hotel-soba.component';

describe('HotelSobaComponent', () => {
  let component: HotelSobaComponent;
  let fixture: ComponentFixture<HotelSobaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelSobaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelSobaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
