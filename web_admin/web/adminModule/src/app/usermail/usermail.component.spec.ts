import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsermailComponent } from './usermail.component';

describe('UsermailComponent', () => {
  let component: UsermailComponent;
  let fixture: ComponentFixture<UsermailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsermailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsermailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
