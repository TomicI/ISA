import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddAerodromComponent } from './form-add-aerodrom.component';

describe('FormAddAerodromComponent', () => {
  let component: FormAddAerodromComponent;
  let fixture: ComponentFixture<FormAddAerodromComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormAddAerodromComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormAddAerodromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
