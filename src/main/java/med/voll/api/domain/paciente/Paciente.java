package med.voll.api.domain.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.medico.DatosActualizarMedico;
import med.voll.api.domain.medico.DatosRegistroMedico;
import med.voll.api.domain.medico.Especialidad;
import med.voll.api.domain.medico.Medico;

@Table(name="pacientes")
@Entity(name= "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public @Data class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private String email;
	private String telefono;
	private String documento;
	private boolean activo;
	@Enumerated(EnumType.STRING)
	private Especialidad especialidad;
	@Embedded
	private Direccion direccion;
	
	public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
		this.activo = true;
		this.nombre = datosRegistroPaciente.nombre();
		this.email = datosRegistroPaciente.email();
		this.telefono = datosRegistroPaciente.telefono();
		this.documento = datosRegistroPaciente.documento();
		this.direccion = new Direccion(datosRegistroPaciente.direccion());
	}

	public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
		
		if(datosActualizarMedico.nombre() != null) {
			this.nombre = datosActualizarMedico.nombre();
		
		}
		
		if(datosActualizarMedico.documento() != null) {
			this.documento = datosActualizarMedico.documento();
		}
		
		if(datosActualizarMedico.direccion() != null) {
			this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
		}
	}
	
	
	public void desactivarMedico() {
		this.activo = false; 
	

}
}
