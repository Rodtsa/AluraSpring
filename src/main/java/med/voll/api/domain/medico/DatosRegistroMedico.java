package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.DatosDireccion;

public record DatosRegistroMedico(
		@NotBlank
		String nombre,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String telefono,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String documento,
		@NotNull //Al ser especialidad un enum siempre va a llegar null 
		Especialidad especialidad,
		@NotNull //Ya que DatosDireccion es un objeto siempre va a llegar null, no blank
		@Valid
		DatosDireccion direccion) {

}
