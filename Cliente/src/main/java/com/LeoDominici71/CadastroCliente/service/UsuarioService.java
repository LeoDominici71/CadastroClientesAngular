package com.LeoDominici71.CadastroCliente.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.LeoDominici71.CadastroCliente.model.entity.Usuario;
import com.LeoDominici71.CadastroCliente.model.repository.UsuarioRepository;
import com.LeoDominici71.CadastroCliente.rest.exception.UsuarioCadastradoException;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;


	public Usuario insert(Usuario usuario) {
		boolean exists = repository.existsByUsername(usuario.getUsername());
		if(exists) {
			 throw new UsuarioCadastradoException(usuario.getUsername());
		}
		return repository.save(usuario);

	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Login nao encontrado."));

		return User.builder().username(usuario.getUsername()).password(usuario.getPassword()).roles("USER").build();
	}

}
