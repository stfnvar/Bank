import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NevalidanLinkComponent } from './nevalidan-link.component';

describe('NevalidanLinkComponent', () => {
  let component: NevalidanLinkComponent;
  let fixture: ComponentFixture<NevalidanLinkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NevalidanLinkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NevalidanLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
