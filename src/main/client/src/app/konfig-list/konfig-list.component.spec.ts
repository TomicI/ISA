import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KonfigListComponent } from './konfig-list.component';

describe('KonfigListComponent', () => {
  let component: KonfigListComponent;
  let fixture: ComponentFixture<KonfigListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KonfigListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KonfigListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
