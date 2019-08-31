import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormKonfLetaComponent } from './form-konf-leta.component';

describe('FormKonfLetaComponent', () => {
  let component: FormKonfLetaComponent;
  let fixture: ComponentFixture<FormKonfLetaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormKonfLetaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormKonfLetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
