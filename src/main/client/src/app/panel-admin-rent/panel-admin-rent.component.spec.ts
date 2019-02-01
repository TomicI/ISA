import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelAdminRentComponent } from './panel-admin-rent.component';

describe('PanelAdminRentComponent', () => {
  let component: PanelAdminRentComponent;
  let fixture: ComponentFixture<PanelAdminRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PanelAdminRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelAdminRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
