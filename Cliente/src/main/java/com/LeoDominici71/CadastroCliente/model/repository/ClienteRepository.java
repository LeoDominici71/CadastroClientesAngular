package com.LeoDominici71.CadastroCliente.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LeoDominici71.CadastroCliente.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
