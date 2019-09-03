import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchLetComponent } from './search-let.component';

describe('SearchLetComponent', () => {
  let component: SearchLetComponent;
  let fixture: ComponentFixture<SearchLetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchLetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchLetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
