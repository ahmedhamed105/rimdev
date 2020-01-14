import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlowtypeComponent } from './flowtype.component';

describe('FlowtypeComponent', () => {
  let component: FlowtypeComponent;
  let fixture: ComponentFixture<FlowtypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlowtypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlowtypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
