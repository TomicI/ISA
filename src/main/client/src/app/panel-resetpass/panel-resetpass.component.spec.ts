import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelResetpassComponent } from './panel-resetpass.component';

describe('PanelResetpassComponent', () => {
  let component: PanelResetpassComponent;
  let fixture: ComponentFixture<PanelResetpassComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PanelResetpassComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelResetpassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
