import { Component } from '@angular/core';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent {
 idAbogado: number = 1; 
  casoSelect!:number;
  casos:any=[];

  formNew:boolean=false;
  tareasView:boolean=false;

  constructor(private casoService: ServiceService) { 
    this.cargarCasos();
  }
  cargarCasos(): void {
    this.casoService.obtenerCasosByCliente(this.idAbogado).subscribe(
      (data: any[]) => {
        console.log(data);
        
        this.casos = data;
      }
    );
  }

  cambiarEstado=()=>{
    this.formNew=!this.formNew;
  }

  verTareas=(caso:any|undefined)=>{
    if(caso!=undefined){
      this.casoSelect= caso.idCaso;
    }
    console.log(this.casoSelect);
    this.tareasView=!this.tareasView
  }

}
