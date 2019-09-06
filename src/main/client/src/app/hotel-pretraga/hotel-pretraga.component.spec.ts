import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelPretragaComponent } from './hotel-pretraga.component';

describe('HotelPretragaComponent', () => {
  let component: HotelPretragaComponent;
  let fixture: ComponentFixture<HotelPretragaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelPretragaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelPretragaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
