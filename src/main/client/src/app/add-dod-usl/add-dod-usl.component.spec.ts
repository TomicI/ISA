import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDodUslComponent } from './add-dod-usl.component';

describe('AddDodUslComponent', () => {
  let component: AddDodUslComponent;
  let fixture: ComponentFixture<AddDodUslComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddDodUslComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDodUslComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
