package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Filter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuariosRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuariosRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Obtener el token del header
				
		var tokenJWT = recuperarToken(request);//
		if(tokenJWT != null) {
			System.out.println("no es nulo");
			var subject = tokenService.getSubject(tokenJWT);
			
			//System.out.println(tokenService.getSubject(token)); //Este usuario tiene sesion?
			System.out.println("El sujeto es: " + subject);
			var usuario = usuarioRepository.findByLogin(subject);
			System.out.println("El sujeto no es nulo");
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); //Forzamos inicio de sesion
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}
	
	
	 private String recuperarToken(HttpServletRequest request) {
	        var authorizationHeader = request.getHeader("Authorization");
	        if (authorizationHeader != null) {
	            return authorizationHeader.replace("Bearer ", "");
	        }

	        return null;
	    }

}
