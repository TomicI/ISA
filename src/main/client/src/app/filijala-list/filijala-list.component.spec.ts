import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilijalaListComponent } from './filijala-list.component';

describe('FilijalaListComponent', () => {
  let component: FilijalaListComponent;
  let fixture: ComponentFixture<FilijalaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilijalaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilijalaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
