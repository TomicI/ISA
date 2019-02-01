import { TestBed } from '@angular/core/testing';

import { LetService } from './let.service';

describe('LetService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LetService = TestBed.get(LetService);
    expect(service).toBeTruthy();
  });
});
