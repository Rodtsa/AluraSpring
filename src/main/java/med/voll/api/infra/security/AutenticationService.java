package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import med.voll.api.domain.usuarios.UsuariosRepository;

@Service
public class AutenticationService  implements UserDetailsService{

	@Autowired
	private UsuariosRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return usuarioRepository.findByLogin(username);
	}

}
