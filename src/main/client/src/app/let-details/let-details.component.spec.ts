import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LetDetailsComponent } from './let-details.component';

describe('LetDetailsComponent', () => {
  let component: LetDetailsComponent;
  let fixture: ComponentFixture<LetDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LetDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LetDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
