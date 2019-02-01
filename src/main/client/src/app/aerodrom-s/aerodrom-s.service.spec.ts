import { TestBed } from '@angular/core/testing';

import { AerodromSService } from './aerodrom-s.service';

describe('AerodromSService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AerodromSService = TestBed.get(AerodromSService);
    expect(service).toBeTruthy();
  });
});
