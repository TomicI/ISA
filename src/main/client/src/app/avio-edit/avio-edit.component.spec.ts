import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvioEditComponent } from './avio-edit.component';

describe('AvioEditComponent', () => {
  let component: AvioEditComponent;
  let fixture: ComponentFixture<AvioEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvioEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvioEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
