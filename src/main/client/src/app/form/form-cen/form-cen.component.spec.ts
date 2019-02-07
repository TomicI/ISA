import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCenComponent } from './form-cen.component';

describe('FormCenComponent', () => {
  let component: FormCenComponent;
  let fixture: ComponentFixture<FormCenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormCenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormCenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
