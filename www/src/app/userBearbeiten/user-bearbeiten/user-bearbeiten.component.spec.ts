import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserBearbeitenComponent } from './user-bearbeiten.component';

describe('UserBearbeitenComponent', () => {
  let component: UserBearbeitenComponent;
  let fixture: ComponentFixture<UserBearbeitenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserBearbeitenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserBearbeitenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
