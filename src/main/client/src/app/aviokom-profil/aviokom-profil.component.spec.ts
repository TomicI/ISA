import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AviokomProfilComponent } from './aviokom-profil.component';

describe('AviokomProfilComponent', () => {
  let component: AviokomProfilComponent;
  let fixture: ComponentFixture<AviokomProfilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AviokomProfilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AviokomProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
