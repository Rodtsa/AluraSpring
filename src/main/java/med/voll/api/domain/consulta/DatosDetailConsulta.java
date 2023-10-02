package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DatosDetailConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {

}
