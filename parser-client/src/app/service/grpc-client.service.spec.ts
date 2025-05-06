import { TestBed } from '@angular/core/testing';

import { GrpcClientService } from './grpc-client.service';

describe('GrpcClientService', () => {
  let service: GrpcClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GrpcClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
