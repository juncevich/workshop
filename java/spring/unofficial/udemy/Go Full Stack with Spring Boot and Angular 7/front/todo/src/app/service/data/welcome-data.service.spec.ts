import { TestBed, inject } from '@angular/core/testing';

import { WelcomeDataService } from './welcome-data.service';

describe('WelcomeDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WelcomeDataService]
    });
  });

  it('should be created', inject([WelcomeDataService], (service: WelcomeDataService) => {
    expect(service).toBeTruthy();
  }));
});
