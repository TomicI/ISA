import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddKatSedistaComponent } from './add-kat-sedista.component';

describe('AddKatSedistaComponent', () => {
  let component: AddKatSedistaComponent;
  let fixture: ComponentFixture<AddKatSedistaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddKatSedistaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddKatSedistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
