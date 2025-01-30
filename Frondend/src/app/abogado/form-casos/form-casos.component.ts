import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceService } from 'src/app/service/service.service';

@Component({
  selector: 'app-form-casos',
  templateUrl: './form-casos.component.html',
  styleUrls: ['./form-casos.component.css']
})
export class FormCasosComponent implements OnInit {
  @Input() cambiarEstado!:()=> void;
  casoForm!: FormGroup;


  tipos = [
    { id: 1, nombre: 'Civil' },
  ];

  clientes = [
    { id: 2, nombre: 'Cliente 1' },
  ];

  abogados = [
    { id: 1, nombre: 'Abogado 1' },
  ];


  constructor(private fb: FormBuilder,
    private casoService: ServiceService
  ) {}

  regresar(){
    this.cambiarEstado();
  }

  ngOnInit(): void {
    this.casoForm = this.fb.group({
      codigo: ['', Validators.required],
      fechaInicio: ['', Validators.required],
      descripcion: ['', Validators.required],
      idTipo: ['', Validators.required],
      idCliente: ['', Validators.required],
      idAbogado: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.casoForm.valid) {
      this.casoService.crearCaso(this.casoForm.value).subscribe(
        (response) => {
          console.log('Caso creado exitosamente:', response);
          alert('Caso creado exitosamente'); 
          this.regresar();
        },
        (error) => {
          console.error('Error al crear el caso:', error);
          alert('Error al crear el caso: ' + error.message);
        }
      );
    } else {
      console.log('Formulario inválido');
    }
  }
}
