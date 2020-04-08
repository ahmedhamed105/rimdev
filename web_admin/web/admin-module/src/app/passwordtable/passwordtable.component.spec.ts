import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordtableComponent } from './passwordtable.component';

describe('PasswordtableComponent', () => {
  let component: PasswordtableComponent;
  let fixture: ComponentFixture<PasswordtableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PasswordtableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PasswordtableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
