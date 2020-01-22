import { TestBed } from '@angular/core/testing';

import { DeviceServService } from './device-serv.service';

describe('DeviceServService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeviceServService = TestBed.get(DeviceServService);
    expect(service).toBeTruthy();
  });
});
