import { TestBed, inject } from '@angular/core/testing';

import { PlatiUsluguService } from './plati-uslugu.service';

describe('PlatiUsluguService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PlatiUsluguService]
    });
  });

  it('should be created', inject([PlatiUsluguService], (service: PlatiUsluguService) => {
    expect(service).toBeTruthy();
  }));
});
