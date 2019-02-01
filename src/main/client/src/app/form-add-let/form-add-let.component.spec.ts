import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddLetComponent } from './form-add-let.component';

describe('FormAddLetComponent', () => {
  let component: FormAddLetComponent;
  let fixture: ComponentFixture<FormAddLetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormAddLetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormAddLetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
