package com.LeoDominici71.CadastroCliente.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, length = 255)
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	@Column
	private BigDecimal valor;

}
