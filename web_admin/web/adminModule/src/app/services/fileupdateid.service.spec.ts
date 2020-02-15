import { TestBed } from '@angular/core/testing';

import { FileupdateidService } from './fileupdateid.service';

describe('FileupdateidService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FileupdateidService = TestBed.get(FileupdateidService);
    expect(service).toBeTruthy();
  });
});
