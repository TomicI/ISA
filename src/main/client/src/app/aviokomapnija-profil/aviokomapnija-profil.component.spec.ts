import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AviokomapnijaProfilComponent } from './aviokomapnija-profil.component';

describe('AviokomapnijaProfilComponent', () => {
  let component: AviokomapnijaProfilComponent;
  let fixture: ComponentFixture<AviokomapnijaProfilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AviokomapnijaProfilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AviokomapnijaProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
