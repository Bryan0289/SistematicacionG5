import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbogadoComponent } from './abogado.component';

describe('AbogadoComponent', () => {
  let component: AbogadoComponent;
  let fixture: ComponentFixture<AbogadoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbogadoComponent]
    });
    fixture = TestBed.createComponent(AbogadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
