import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormFilComponent } from './form-fil.component';

describe('FormFilComponent', () => {
  let component: FormFilComponent;
  let fixture: ComponentFixture<FormFilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormFilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormFilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
