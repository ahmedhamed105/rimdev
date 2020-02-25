import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserteleComponent } from './usertele.component';

describe('UserteleComponent', () => {
  let component: UserteleComponent;
  let fixture: ComponentFixture<UserteleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserteleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserteleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
