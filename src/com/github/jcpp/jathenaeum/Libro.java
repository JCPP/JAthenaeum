/**
 * 
 */
package com.github.jcpp.jathenaeum;

/**
 * Libro class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Libro {
	private int id;
	private String titolo;
	private String copertina;
	private String genere;
	private String codiceIsbn;
	private String descrizione;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}
	/**
	 * @param titolo the titolo to set
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	/**
	 * @return the copertina
	 */
	public String getCopertina() {
		return copertina;
	}
	/**
	 * @param copertina the copertina to set
	 */
	public void setCopertina(String copertina) {
		this.copertina = copertina;
	}
	/**
	 * @return the genere
	 */
	public String getGenere() {
		return genere;
	}
	/**
	 * @param genere the genere to set
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}
	/**
	 * @return the codiceIsbn
	 */
	public String getCodiceIsbn() {
		return codiceIsbn;
	}
	/**
	 * @param codiceIsbn the codiceIsbn to set
	 */
	public void setCodiceIsbn(String codiceIsbn) {
		this.codiceIsbn = codiceIsbn;
	}
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}
