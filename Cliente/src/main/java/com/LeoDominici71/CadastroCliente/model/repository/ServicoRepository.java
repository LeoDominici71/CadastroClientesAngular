package com.LeoDominici71.CadastroCliente.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LeoDominici71.CadastroCliente.model.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer>{

	@Query(" select s from Servico s join s.cliente c" + 
	" where upper(c.nome) like upper(:nome) and MONTH(s.data) = :mes ")
	List<Servico> findAllByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);

}
