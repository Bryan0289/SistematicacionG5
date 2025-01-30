import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AbogadoComponent } from './abogado.component';

const routes: Routes = [{ path: '', component: AbogadoComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AbogadoRoutingModule { }
