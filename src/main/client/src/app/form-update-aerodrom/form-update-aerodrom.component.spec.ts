import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormUpdateAerodromComponent } from './form-update-aerodrom.component';

describe('FormUpdateAerodromComponent', () => {
  let component: FormUpdateAerodromComponent;
  let fixture: ComponentFixture<FormUpdateAerodromComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormUpdateAerodromComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormUpdateAerodromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
