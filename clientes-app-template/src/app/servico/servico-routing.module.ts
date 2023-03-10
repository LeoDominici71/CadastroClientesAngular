import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from '../layout/layout.component';
import { ServicoFormComponent } from './servico-form/servico-form.component';
import { ServicoListaComponent } from './servico-lista/servico-lista.component';
import { AuthGuard } from '../auth.guard'


const routes: Routes = [
  { path: 'servico', canActivate: [AuthGuard], component: LayoutComponent, children: [
    {path: 'form', component:ServicoFormComponent},
    {path: 'lista', component:ServicoListaComponent},
    {path: '', redirectTo: '/servico/lista', pathMatch: 'full'}
  ]}
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServicoRoutingModule { }
