import { Component, OnInit } from '@angular/core';
import {Cliente} from '../../clientes/cliente'
import{ClientesService} from '../../clientes.service'
import { Servico } from '../Servico';
import { ServicoService } from '../../servico.service'

@Component({
  selector: 'app-servico-form',
  templateUrl: './servico-form.component.html',
  styleUrls: ['./servico-form.component.css']
})
export class ServicoFormComponent implements OnInit {

  clientes : Cliente[] = []
  servicoPrestado: Servico;
  success: boolean = false;
  errors: String[];
  constructor(
    private clienteService: ClientesService,
    private service : ServicoService
  ) {
    this.servicoPrestado = new Servico();
   }

  ngOnInit(): void {
    this.clienteService
    .getClientes()
    .subscribe(response => this.clientes = response);
  }

  onSubmit(){
    this.service.salvar(this.servicoPrestado)
    .subscribe(response => {
      this.success = true;
      this.errors = null;
      this.servicoPrestado = new Servico();
    }, errorResponse => {
      this.success = false;
      this.errors = ['Erro ao salvar o cliente.']
    })
  }

}
