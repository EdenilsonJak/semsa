package com.ejsistemas.semsa.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.repository.filter.PacienteFilter;
import com.ejsistemas.semsa.service.CadastroPacienteService;
import com.ejsistemas.semsa.service.WebServiceCep;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroPacienteService cadastroPacienteService;

	private List<Paciente> pacientes;

	private Paciente paciente;

	private int activeIndex;
	
	private WebServiceCep webServiceCep;

	public CadastroPacienteBean() {
		limpar();
	}

	public void buscarCep() throws JSONException, Exception {
		// define a url
		String url = "http://cep.republicavirtual.com.br/web_cep.php?cep=" + paciente.getCep() + "&formato=json";

		// define os dados
		JSONObject obj = new JSONObject(getHttpGET(url));

		if (obj.getString("resultado").equals("1")) {

			paciente.setTipoLogradouro(obj.getString("tipo_logradouro"));
			paciente.setLogradouro(obj.getString("logradouro"));
			paciente.setBairro(obj.getString("bairro"));
			paciente.setCidade(obj.getString("cidade"));
			paciente.setUf(obj.getString("uf"));
			// paciente.setComplemento(obj.getString("complemento"));

		} else if (obj.getString("resultado").equals("0")) {
			// verifica os Eventos

			paciente.setTipoLogradouro(null);
			paciente.setLogradouro(null);
			paciente.setBairro(null);
			paciente.setCidade(null);
			paciente.setUf(null);
			// paciente.setComplemento(null);
			FacesUtil.addErrorMessage("Não foi possível encontrar o cep!");
		} else {
			paciente.setTipoLogradouro(null);
			paciente.setLogradouro(null);
			paciente.setBairro(null);
			paciente.setCidade(null);
			paciente.setUf(null);
			// paciente.setComplemento(null);
			FacesUtil.addErrorMessage(" Não foi possível estabelecer conexão!");
		}

	}

	public final String getHttpGET(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		JSONObject obj = new JSONObject();

		try {
			URL url = new URL(urlToRead);
			URLConnection con = url.openConnection();
			con.setConnectTimeout(1 * 1000);
			con.setReadTimeout(2 * 1000);
			HttpURLConnection conn = (HttpURLConnection) con;
			conn.setRequestMethod("GET");

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

		} catch (Exception ex) {
			// verifica os Eventos
			FacesUtil.addErrorMessage("Conexeção ao web service falhou! " + ex);

		}

		if (result.length() > 0) {
			return result.toString();
		} else {
			obj.put("resultado", "0");
			return obj.toString();
		}

	}

	public void salvar() {
		if (paciente.isNovo()) {
			if (existePaciente(paciente)) {
				FacesUtil.addAlerMessage("Paciente já cadastrado!");
			} else {
					if(isValid(this.paciente.getSus())){
						this.paciente.setDataCriacao(new Date());
				
					this.paciente = cadastroPacienteService.salvar(paciente);
					this.paciente = new Paciente();
					FacesUtil.addInfoMessage("Paciente cadastrado com sucesso!");
					paciente = new Paciente();
					}
						else{
							FacesUtil.addAlerMessage("Cartao sus não é válido!");
						}
				}
		} else {
			 if(isValid(this.paciente.getSus())){ 
			this.paciente = cadastroPacienteService.salvar(paciente);
			FacesUtil.addInfoMessage("Paciente atualizado com sucesso!");
			paciente = new Paciente();
		}
		
		  else{
			  FacesUtil.addAlerMessage("Cartao sus não é válido!"); 
		  }
		  }
		 

}

	private boolean existePaciente(Paciente paciente) {
		boolean existepaciente;
	    List<Paciente>	pacienteConsulta = cadastroPacienteService.porNomeCns(paciente.getSus());
			if (pacienteConsulta.isEmpty()) {
				existepaciente = false;
			}else{
				existepaciente = true;
			}

		return existepaciente;
	}

	public boolean isValid(String s) {
		if (s.matches("[1-2]\\d{10}00[0-1]\\d") || s.matches("[7-9]\\d{14}")) {
			return somaPonderada(s) % 11 == 0;
		}
		return false;
	}

	private int somaPonderada(String s) {
		char[] cs = s.toCharArray();
		int soma = 0;
		for (int i = 0; i < cs.length; i++) {
			soma += Character.digit(cs[i], 10) * (15 - i);
		}
		return soma;
	}

	public void consultaCep() {
		webServiceCep = WebServiceCep.searchCep(this.paciente.getCep());

		if (webServiceCep.wasSuccessful()) {
			this.paciente.setTipoLogradouro(webServiceCep.getLogradouroType());
			this.paciente.setLogradouro(webServiceCep.getLogradouro());
			this.paciente.setBairro(webServiceCep.getBairro());
			this.paciente.setCidade(webServiceCep.getCidade());
			this.paciente.setUf(webServiceCep.getUf());
		} else {
			FacesUtil.addAlerMessage("Cep Incorreto!");
		}

	}

	public void recebePaciente(Paciente paciente, int indice){
		setPaciente(paciente);
		goToTab(0);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("tabPanelWidget");
	}
	
	public void limpar() {
		paciente = new Paciente();
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public void goToTab(int index){
		this.activeIndex = index;
	}
	
}
