import { TestBed } from '@angular/core/testing';

import { EntityGenerateService } from './entity-generate.service';

describe('EntityGenerateService', () => {
  let service: EntityGenerateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EntityGenerateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
