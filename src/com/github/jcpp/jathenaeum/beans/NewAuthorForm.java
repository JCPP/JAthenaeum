package com.github.jcpp.jathenaeum.beans;

import org.apache.struts.action.ActionForm;

public class NewAuthorForm extends ActionForm{

	private static final long serialVersionUID = 1;
	
	private String nome;
	private String cognome;
	private String foto;
	private String data;
	private String biografia;
	
	public NewAuthorForm() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	
}
