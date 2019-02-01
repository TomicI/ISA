import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAviokAerComponent } from './add-aviok-aer.component';

describe('AddAviokAerComponent', () => {
  let component: AddAviokAerComponent;
  let fixture: ComponentFixture<AddAviokAerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAviokAerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAviokAerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
