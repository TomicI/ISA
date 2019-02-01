import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRentComponent } from './form-rent.component';

describe('FormRentComponent', () => {
  let component: FormRentComponent;
  let fixture: ComponentFixture<FormRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
