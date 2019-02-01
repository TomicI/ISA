import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddKartaComponent } from './form-add-karta.component';

describe('FormAddKartaComponent', () => {
  let component: FormAddKartaComponent;
  let fixture: ComponentFixture<FormAddKartaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormAddKartaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormAddKartaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
