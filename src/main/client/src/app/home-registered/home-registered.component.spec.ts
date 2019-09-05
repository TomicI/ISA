import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeRegisteredComponent } from './home-registered.component';

describe('HomeRegisteredComponent', () => {
  let component: HomeRegisteredComponent;
  let fixture: ComponentFixture<HomeRegisteredComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeRegisteredComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeRegisteredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
