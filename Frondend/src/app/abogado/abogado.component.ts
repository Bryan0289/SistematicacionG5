import { Component } from '@angular/core';
import { ServiceService } from '../service/service.service';


@Component({
  selector: 'app-abogado',
  templateUrl: './abogado.component.html',
  styleUrls: ['./abogado.component.css']
})
export class AbogadoComponent {

  idAbogado: number = 1; 
  casoSelect!:number;
  casos:any=[];

  formNew:boolean=false;
  tareasView:boolean=false;

  constructor(private casoService: ServiceService) { 
    this.cargarCasos();
  }
  cargarCasos(): void {
    this.casoService.obtenerCasos(this.idAbogado).subscribe(
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

  eliminarCaso(caso:any){
    this.casoService.eliminarCaso(caso.idCaso).subscribe(
      (_) => {
       this.cargarCasos();
      }
    );
  }

}
