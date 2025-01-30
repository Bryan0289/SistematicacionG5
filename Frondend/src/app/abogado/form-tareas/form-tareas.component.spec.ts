import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTareasComponent } from './form-tareas.component';

describe('FormTareasComponent', () => {
  let component: FormTareasComponent;
  let fixture: ComponentFixture<FormTareasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormTareasComponent]
    });
    fixture = TestBed.createComponent(FormTareasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
