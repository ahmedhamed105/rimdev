import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsertypedropdownComponent } from './usertypedropdown.component';

describe('UsertypedropdownComponent', () => {
  let component: UsertypedropdownComponent;
  let fixture: ComponentFixture<UsertypedropdownComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsertypedropdownComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsertypedropdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
