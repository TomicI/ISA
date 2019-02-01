import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAKUpdateComponent } from './form-akupdate.component';

describe('FormAKUpdateComponent', () => {
  let component: FormAKUpdateComponent;
  let fixture: ComponentFixture<FormAKUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormAKUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormAKUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
