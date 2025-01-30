import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AbogadoRoutingModule } from './abogado-routing.module';
import { AbogadoComponent } from './abogado.component';
import { FormCasosComponent } from './form-casos/form-casos.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TareasListComponent } from './tareas-list/tareas-list.component';
import { FormTareasComponent } from './form-tareas/form-tareas.component';


@NgModule({
  declarations: [
    AbogadoComponent,
    FormCasosComponent,
    TareasListComponent,
    FormTareasComponent
  ],
  imports: [
    CommonModule,
    AbogadoRoutingModule,
    ReactiveFormsModule
  ]
})
export class AbogadoModule { }
