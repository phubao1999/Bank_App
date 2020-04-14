import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TranfferMonneyComponent } from './tranffer-monney.component';

describe('TranfferMonneyComponent', () => {
  let component: TranfferMonneyComponent;
  let fixture: ComponentFixture<TranfferMonneyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TranfferMonneyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TranfferMonneyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
