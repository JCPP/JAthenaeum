/**
 * 
 */
package com.github.jcpp.jathenaeum;

import java.util.Date;

/**
 * Prestito class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Prestito {
	
	private int id;
	private int numeroTesseraUtente;
	private int idLibro;
	private Date dataInizio;
	private Date dataFine;
	
	
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
	 * @return the numeroTesseraUtente
	 */
	public int getNumeroTesseraUtente() {
		return numeroTesseraUtente;
	}
	/**
	 * @param numeroTesseraUtente the numeroTesseraUtente to set
	 */
	public void setNumeroTesseraUtente(int numeroTesseraUtente) {
		this.numeroTesseraUtente = numeroTesseraUtente;
	}
	/**
	 * @return the idLibro
	 */
	public int getIdLibro() {
		return idLibro;
	}
	/**
	 * @param idLibro the idLibro to set
	 */
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	/**
	 * @return the dataInizio
	 */
	public Date getDataInizio() {
		return dataInizio;
	}
	/**
	 * @param dataInizio the dataInizio to set
	 */
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	/**
	 * @return the dataFine
	 */
	public Date getDataFine() {
		return dataFine;
	}
	/**
	 * @param dataFine the dataFine to set
	 */
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

}
