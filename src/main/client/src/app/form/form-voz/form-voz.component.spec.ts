import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormVozComponent } from './form-voz.component';

describe('FormVozComponent', () => {
  let component: FormVozComponent;
  let fixture: ComponentFixture<FormVozComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormVozComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormVozComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
