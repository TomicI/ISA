import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPrtljagComponent } from './add-prtljag.component';

describe('AddPrtljagComponent', () => {
  let component: AddPrtljagComponent;
  let fixture: ComponentFixture<AddPrtljagComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPrtljagComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPrtljagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
