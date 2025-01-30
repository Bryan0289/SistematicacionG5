import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCasosComponent } from './form-casos.component';

describe('FormCasosComponent', () => {
  let component: FormCasosComponent;
  let fixture: ComponentFixture<FormCasosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormCasosComponent]
    });
    fixture = TestBed.createComponent(FormCasosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
