package com.brasilprev.loja.api.seguranca;

import static java.util.Collections.emptyList;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ValidaUsuario implements UserDetailsService {

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    ValidaUsuario(ClienteRepositorio repositorio) {
        this.clienteRepositorio = repositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {

        Cliente cliente = clienteRepositorio.findByNome(nome);

        if (cliente == null)
            throw new UsernameNotFoundException(nome);

        return new User(cliente.getNome(), cliente.getSenha(), emptyList());
    }
}
