package com.LeoDominici71.CadastroCliente.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.LeoDominici71.CadastroCliente.model.entity.Cliente;
import com.LeoDominici71.CadastroCliente.model.entity.Servico;
import com.LeoDominici71.CadastroCliente.model.repository.ClienteRepository;
import com.LeoDominici71.CadastroCliente.model.repository.ServicoRepository;
import com.LeoDominici71.CadastroCliente.rest.dto.ServicoDTO;
import com.LeoDominici71.CadastroCliente.util.BigDecimalConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicoController {

	private final ClienteRepository clienteRepository;
	private final ServicoRepository repository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Servico salvar(@RequestBody @Valid ServicoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));
		
		Servico servico = new Servico();
		servico.setDescricao(dto.getDescricao());
		servico.setData(data);
		servico.setCliente(cliente);
		servico.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return repository.save(servico);

	}
	
	@GetMapping
	public List<Servico> pesquisar(
		@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
		@RequestParam(value = "mes", required = false) Integer mes
		){
		return repository.findAllByNomeClienteAndMes("%" + nome + "%", mes);
		
	}
}
