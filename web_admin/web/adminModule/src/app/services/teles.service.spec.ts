import { TestBed } from '@angular/core/testing';

import { TelesService } from './teles.service';

describe('TelesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TelesService = TestBed.get(TelesService);
    expect(service).toBeTruthy();
  });
});
