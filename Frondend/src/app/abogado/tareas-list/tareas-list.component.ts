import { Component, Input, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service/service.service';

@Component({
  selector: 'app-tareas-list',
  templateUrl: './tareas-list.component.html',
  styleUrls: ['./tareas-list.component.css'],
})
export class TareasListComponent implements OnInit {
  @Input() cambiarEstado!: (caso: number | undefined) => void;
  @Input() idCaso!: number;

  tareas: any = [];
  viewForm:boolean=false;

  constructor(private tareaService: ServiceService) {}
  ngOnInit(): void {
    this.cargartareas();
  }
  cargartareas(): void {
    console.log(this.idCaso);

    this.tareaService.obtenerTareas(this.idCaso).subscribe((data: any[]) => {
      console.log(data);

      this.tareas = data;
    });
  }

  regresarCasos() {
    this.cambiarEstado(undefined);
  }

  crearTarea(){
    this.viewForm=!this.viewForm;
  }
}
