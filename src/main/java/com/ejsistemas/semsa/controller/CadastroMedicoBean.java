package com.ejsistemas.semsa.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.repository.filter.MedicoFilter;
import com.ejsistemas.semsa.service.CadastroMedicoService;
import com.ejsistemas.semsa.service.WebServiceCep;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroMedicoService cadastroMedicoService;

	private List<Medico> medicos;
	
	private int activeIndex;

	private Medico medico;
	private WebServiceCep webServiceCep;

	public CadastroMedicoBean() {
		limpar();
	}

	public void inicializar() {
		this.medicos = cadastroMedicoService.buscarTodos(new MedicoFilter());
	}

	public void salvar() {
		if (medico.isNovo()) {
			if (existeMedico(medico)) {
			} else {
				if(isValid(this.medico.getNr_sus())){
					this.medico.setDataCadastro(new Date());
					this.medico = cadastroMedicoService.salvar(medico);
					this.medico = new Medico();
					FacesUtil.addInfoMessage("Profissional médico cadastrado com sucesso!");
				}
				else{
					FacesUtil.addAlerMessage("Cartao sus Profissional não é válido!");
				}
				

			}
		} else {
			if(isValid(this.medico.getNr_sus())){
				this.medico = cadastroMedicoService.salvar(medico);
				FacesUtil.addInfoMessage("Profissional médico atualizado com sucesso!");
				this.medico = new Medico();
			}
			else{
				FacesUtil.addAlerMessage("Cartao sus Profissional não é válido!");
			}
			
			
		}

	}

	public void limpar() {
		this.medico = new Medico();
		this.medicos = new ArrayList<>();
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void consultaCep() {
		webServiceCep = WebServiceCep.searchCep(this.medico.getCep());

		if (webServiceCep.wasSuccessful()) {
			this.medico.setTipo_logradouro(webServiceCep.getLogradouroType());
			this.medico.setLogradouro(webServiceCep.getLogradouro());
			this.medico.setBairro(webServiceCep.getBairro());
			this.medico.setCidade(webServiceCep.getCidade());
			this.medico.setEstado(webServiceCep.getUf());
		} else {
			FacesUtil.addAlerMessage("Cep Incorreto!");
		}

	}
	
	
	public void buscarCep() throws JSONException, Exception {
		// define a url
		String url = "http://cep.republicavirtual.com.br/web_cep.php?cep=" + medico.getCep() + "&formato=json";

		// define os dados
		JSONObject obj = new JSONObject(getHttpGET(url));

		if (obj.getString("resultado").equals("1")) {

			medico.setTipo_logradouro(obj.getString("tipo_logradouro"));
			medico.setLogradouro(obj.getString("logradouro"));
			medico.setBairro(obj.getString("bairro"));
			medico.setCidade(obj.getString("cidade"));
			medico.setEstado(obj.getString("uf"));
			// medico.setComplemento(obj.getString("complemento"));

		} else if (obj.getString("resultado").equals("0")) {
			// verifica os Eventos

			medico.setTipo_logradouro(null);
			medico.setLogradouro(null);
			medico.setBairro(null);
			medico.setCidade(null);
			medico.setEstado(null);
			// medico.setComplemento(null);
			FacesUtil.addErrorMessage("Não foi possível encontrar o cep!");
		} else {
			medico.setTipo_logradouro(null);
			medico.setLogradouro(null);
			medico.setBairro(null);
			medico.setCidade(null);
			medico.setEstado(null);
			// medico.setComplemento(null);
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

	
	public void receberMedico(Medico medico, int linha){
		setMedico(medico);
		goToTab(0);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("tabPanelWidget");
	}

	private boolean existeMedico(Medico medico) {
		boolean existeMedico = false;
		this.medicos = cadastroMedicoService.buscarTodos(new MedicoFilter());

		for (Medico medicoConsulta : this.medicos) {
			if (medico.getNr_sus() != null && medico.getNr_sus() != "") {
				if(medico.getNr_sus().equals(medicoConsulta.getNr_sus())){
					existeMedico = true;
					FacesUtil.addAlerMessage("CNS: " +medico.getNr_sus()+ " já cadastrado!");
					break;
				}
				
			}
			if(medico.getCpf() != null && medico.getCpf() != ""){
				if(medico.getCpf().equals(medicoConsulta.getCpf())){
					existeMedico = true;
					FacesUtil.addAlerMessage("CPF: " +medico.getCpf()+ " já cadastrado!");
					break;
				}
			}
		}

		return existeMedico;
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
