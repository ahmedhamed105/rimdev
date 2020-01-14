import { TestBed } from '@angular/core/testing';

import { CurrencyServService } from './currency-serv.service';

describe('CurrencyServService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CurrencyServService = TestBed.get(CurrencyServService);
    expect(service).toBeTruthy();
  });
});
