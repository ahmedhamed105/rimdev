import { TestBed } from '@angular/core/testing';

import { StatusServService } from './status-serv.service';

describe('StatusServService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StatusServService = TestBed.get(StatusServService);
    expect(service).toBeTruthy();
  });
});
