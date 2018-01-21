import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlatiUsluguComponent } from './plati-uslugu.component';

describe('PlatiUsluguComponent', () => {
  let component: PlatiUsluguComponent;
  let fixture: ComponentFixture<PlatiUsluguComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlatiUsluguComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlatiUsluguComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
