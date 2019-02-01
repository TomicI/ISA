import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddDestinacijaComponent } from './form-add-destinacija.component';

describe('FormAddDestinacijaComponent', () => {
  let component: FormAddDestinacijaComponent;
  let fixture: ComponentFixture<FormAddDestinacijaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormAddDestinacijaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormAddDestinacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
