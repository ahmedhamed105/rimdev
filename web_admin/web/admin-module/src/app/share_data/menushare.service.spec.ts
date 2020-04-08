import { TestBed } from '@angular/core/testing';

import { MenushareService } from './menushare.service';

describe('MenushareService', () => {
  let service: MenushareService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenushareService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
