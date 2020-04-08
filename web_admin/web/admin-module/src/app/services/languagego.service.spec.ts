import { TestBed } from '@angular/core/testing';

import { LanguagegoService } from './languagego.service';

describe('LanguagegoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LanguagegoService = TestBed.get(LanguagegoService);
    expect(service).toBeTruthy();
  });
});
