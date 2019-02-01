import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormDodajAviokomComponent } from './form-dodaj-aviokom.component';

describe('FormDodajAviokomComponent', () => {
  let component: FormDodajAviokomComponent;
  let fixture: ComponentFixture<FormDodajAviokomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormDodajAviokomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormDodajAviokomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
