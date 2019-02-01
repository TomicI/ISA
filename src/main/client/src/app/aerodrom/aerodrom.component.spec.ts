import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AerodromComponent } from './aerodrom.component';

describe('AerodromComponent', () => {
  let component: AerodromComponent;
  let fixture: ComponentFixture<AerodromComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AerodromComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AerodromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
