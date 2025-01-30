import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceService } from 'src/app/service/service.service';

@Component({
  selector: 'app-form-tareas',
  templateUrl: './form-tareas.component.html',
  styleUrls: ['./form-tareas.component.css'],
})
export class FormTareasComponent {
  @Input() cambiarEstado!: () => void;
  @Input() idCaso!: number;

  tareaForm!: FormGroup;

  // Datos de ejemplo para los selects
  estados = ['Pendiente', 'En Progreso', 'Completada'];
  abogados = [
    { id: 1, nombre: 'Abogado 1' },
    { id: 2, nombre: 'Abogado 2' },
    { id: 3, nombre: 'Abogado 3' },
  ];
  casos = [
    { id: 1, codigo: 'C001', descripcion: 'Caso de divorcio' },
    { id: 2, codigo: 'C002', descripcion: 'Caso de herencia' },
    { id: 3, codigo: 'C003', descripcion: 'Caso laboral' },
  ];

  constructor(private fb: FormBuilder,
    private tareaService: ServiceService) {}

  ngOnInit(): void {
    this.tareaForm = this.fb.group({
      descripcion: ['', Validators.required],
      fechaLimite: ['', Validators.required],
      estado: ['', Validators.required],
      idAbogado: ['', Validators.required],
      idCaso: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.tareaForm.valid) {
      const tareaDto = this.tareaForm.value; // Obtiene los valores del formulario

      // Llama al servicio para crear la tarea
      this.tareaService.crearTarea(tareaDto).subscribe(
        (response) => {
          console.log('Tarea creada exitosamente:', response);
          alert('Tarea creada exitosamente'); // Muestra un mensaje de éxito
          this.tareaForm.reset(); // Limpia el formulario
        },
        (error) => {
          console.error('Error al crear la tarea:', error);
          alert('Error al crear la tarea: ' + error.message); // Muestra un mensaje de error
        }
      );
    } else {
      console.log('Formulario inválido');
      alert('Por favor, complete todos los campos requeridos.'); // Muestra un mensaje de validación
    }
  }
}