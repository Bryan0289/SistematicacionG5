import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  private apiUrl = environment.apiUrl+'/api'; 

  constructor(private http: HttpClient) { }

  obtenerCasos(idAbogado: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/caso/${idAbogado}`);
  }
  eliminarCaso(idCaso: number): Observable<any[]> {
    return this.http.delete<any>(`${this.apiUrl}/caso/${idCaso}`);
  }
  obtenerCasosByCliente(idCliente: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/caso/cliente/${idCliente}`);
  }
  obtenerTareas(idCaso: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/tarea/abogado/${idCaso}`);
  }

  crearCaso(casoDto: any): Observable<any> {
    
    return this.http.post(this.apiUrl+'/caso', casoDto);
  }

  crearTarea(tareaDto: any): Observable<any> {
    
    return this.http.post(this.apiUrl+'/tarea', tareaDto);
  }
}
