import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserHinzufuegenComponent } from './user-hinzufuegen.component';

describe('UserHinzufuegenComponent', () => {
  let component: UserHinzufuegenComponent;
  let fixture: ComponentFixture<UserHinzufuegenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserHinzufuegenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserHinzufuegenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
