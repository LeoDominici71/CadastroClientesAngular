import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServicoFormComponent } from './servico-form/servico-form.component';
import { ServicoListaComponent } from './servico-lista/servico-lista.component';


const routes: Routes = [
  {path: 'servico-form', component:ServicoFormComponent},
  {path: 'servico-listagem', component:ServicoListaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServicoRoutingModule { }
