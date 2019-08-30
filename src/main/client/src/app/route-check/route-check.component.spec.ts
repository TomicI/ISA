import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RouteCheckComponent } from './route-check.component';

describe('RouteCheckComponent', () => {
  let component: RouteCheckComponent;
  let fixture: ComponentFixture<RouteCheckComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RouteCheckComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RouteCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
