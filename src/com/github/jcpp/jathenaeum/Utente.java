/**
 * 
 */
package com.github.jcpp.jathenaeum;


/**
 * Utente class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Utente {
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private String dataNascita;
	private int numeroTessera;
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	/**
	 * @return the dataNascita
	 */
	public String getDataNascita() {
		return dataNascita;
	}
	/**
	 * @param string the dataNascita to set
	 */
	public void setDataNascita(String string) {
		this.dataNascita = string;
	}
	/**
	 * @return the numeroTessera
	 */
	public int getNumeroTessera() {
		return numeroTessera;
	}
	/**
	 * @param numeroTessera the numeroTessera to set
	 */
	public void setNumeroTessera(int numeroTessera) {
		this.numeroTessera = numeroTessera;
	}

}
