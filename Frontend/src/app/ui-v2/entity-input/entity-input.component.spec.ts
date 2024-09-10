import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntityInputComponent } from './entity-input.component';

describe('EntityInputComponent', () => {
  let component: EntityInputComponent;
  let fixture: ComponentFixture<EntityInputComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntityInputComponent]
    });
    fixture = TestBed.createComponent(EntityInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
