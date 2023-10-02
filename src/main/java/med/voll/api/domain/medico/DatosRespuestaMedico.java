package med.voll.api.domain.medico;

import med.voll.api.DatosDireccion;

public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento,
									DatosDireccion direccion) {

}
