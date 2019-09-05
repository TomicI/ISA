import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnosPutnikaComponent } from './unos-putnika.component';

describe('UnosPutnikaComponent', () => {
  let component: UnosPutnikaComponent;
  let fixture: ComponentFixture<UnosPutnikaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnosPutnikaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnosPutnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
