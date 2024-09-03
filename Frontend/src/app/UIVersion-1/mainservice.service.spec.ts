import { TestBed } from '@angular/core/testing';

import { MainserviceService } from './mainservice.service';

describe('MainserviceService', () => {
  let service: MainserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MainserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
