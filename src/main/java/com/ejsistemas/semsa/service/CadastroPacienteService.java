package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.repository.PacienteRepository;
import com.ejsistemas.semsa.repository.filter.PacienteFilter;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CadastroPacienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	PacienteRepository pacienteRepository;

	private List<Paciente> pacientes;

	private Paciente paciente;

	@Transactional
	public Paciente salvar(Paciente paciente) {
		return this.paciente = pacienteRepository.guardar(paciente);

	}
	
	public List<Paciente> buscarTodos(){
		return this.pacienteRepository.listarTodos();
	}

	public List<Paciente> buscarFiltrados(PacienteFilter filter){
		return pacienteRepository.filtrados(filter);
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}
	
	public Paciente buscarPorCns(String sus){
		return pacienteRepository.porCNS(sus);
	}

	public Paciente getPaciente() {
		return paciente;
	}
	
	public List<Paciente> porNomeCns(String valor){
		return pacienteRepository.porNomeCNS(valor);
	}

}
