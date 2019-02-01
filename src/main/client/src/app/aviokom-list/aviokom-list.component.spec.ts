import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AviokomListComponent } from './aviokom-list.component';

describe('AviokomListComponent', () => {
  let component: AviokomListComponent;
  let fixture: ComponentFixture<AviokomListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AviokomListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AviokomListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
