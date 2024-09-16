import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntityinputComponent } from './entityinput.component';

describe('EntityinputComponent', () => {
  let component: EntityinputComponent;
  let fixture: ComponentFixture<EntityinputComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntityinputComponent]
    });
    fixture = TestBed.createComponent(EntityinputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
