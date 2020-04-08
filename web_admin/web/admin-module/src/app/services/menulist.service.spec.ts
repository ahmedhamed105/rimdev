import { TestBed } from '@angular/core/testing';

import { MenulistService } from './menulist.service';

describe('MenulistService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MenulistService = TestBed.get(MenulistService);
    expect(service).toBeTruthy();
  });
});
