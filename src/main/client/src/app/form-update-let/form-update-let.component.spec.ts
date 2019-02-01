import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormUpdateLetComponent } from './form-update-let.component';

describe('FormUpdateLetComponent', () => {
  let component: FormUpdateLetComponent;
  let fixture: ComponentFixture<FormUpdateLetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormUpdateLetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormUpdateLetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
