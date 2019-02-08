import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchRentComponent } from './search-rent.component';

describe('SearchRentComponent', () => {
  let component: SearchRentComponent;
  let fixture: ComponentFixture<SearchRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
