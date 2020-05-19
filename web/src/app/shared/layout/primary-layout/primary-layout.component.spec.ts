import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrimaryLayoutComponent } from './primary-layout.component';

describe('PrimaryLayoutComponent', () => {
  let component: PrimaryLayoutComponent;
  let fixture: ComponentFixture<PrimaryLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrimaryLayoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrimaryLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
