import { TestBed } from '@angular/core/testing';

import { FlowtypeServService } from './flowtype-serv.service';

describe('FlowtypeServService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FlowtypeServService = TestBed.get(FlowtypeServService);
    expect(service).toBeTruthy();
  });
});
