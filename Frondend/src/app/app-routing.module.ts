import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'abogado', loadChildren: () => import('./abogado/abogado.module').then(m => m.AbogadoModule) },
  { path: 'usuario', loadChildren: () => import('./usuario/usuario.module').then(m => m.UsuarioModule) },
  { path: '', redirectTo: '/usuario', pathMatch: 'full' }, 
  { path: '**', redirectTo: '/usuario' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
