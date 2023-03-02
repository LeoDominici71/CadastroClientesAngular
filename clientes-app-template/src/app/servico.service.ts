import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Servico } from './servico/Servico';
import { environment } from '../environments/environment'
import { ServicoBusca } from './servico/servico-lista/ServicoBusca';

@Injectable({
  providedIn: 'root'
})
export class ServicoService {

  apiURL: string = environment.apiURLBase + "/api/servicos"

  constructor(private http: HttpClient) { }
  salvar(servico: Servico): Observable<Servico>{
    return this.http.post<Servico>(this.apiURL, servico);
  }
  buscar(nome: string, mes: number) : Observable<ServicoBusca[]>{
    const httpParams = new HttpParams().set("nome", nome).set("mes", mes.toString());
   
    const url = this.apiURL + "?" + httpParams.toString();
    console.log(url);
    return this.http.get<any>(url);
  }
}
