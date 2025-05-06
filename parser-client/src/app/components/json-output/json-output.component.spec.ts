import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JsonOutputComponent } from './json-output.component';

describe('JsonOutputComponent', () => {
  let component: JsonOutputComponent;
  let fixture: ComponentFixture<JsonOutputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JsonOutputComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JsonOutputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
