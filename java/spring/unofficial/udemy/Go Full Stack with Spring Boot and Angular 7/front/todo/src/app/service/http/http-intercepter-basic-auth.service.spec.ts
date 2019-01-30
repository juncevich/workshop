import { TestBed, inject } from '@angular/core/testing';

import { HttpIntercepterBasicAuthService } from './http-intercepter-basic-auth.service';

describe('HttpIntercepterBasicAuthService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HttpIntercepterBasicAuthService]
    });
  });

  it('should be created', inject([HttpIntercepterBasicAuthService], (service: HttpIntercepterBasicAuthService) => {
    expect(service).toBeTruthy();
  }));
});
