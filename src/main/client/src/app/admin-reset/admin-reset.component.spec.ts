import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminResetComponent } from './admin-reset.component';

describe('AdminResetComponent', () => {
  let component: AdminResetComponent;
  let fixture: ComponentFixture<AdminResetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminResetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminResetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
