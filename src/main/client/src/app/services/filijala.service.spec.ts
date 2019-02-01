import { TestBed } from '@angular/core/testing';

import { FilijalaService } from './filijala.service';

describe('FilijalaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FilijalaService = TestBed.get(FilijalaService);
    expect(service).toBeTruthy();
  });
});
