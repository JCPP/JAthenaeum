package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.Libro;
import com.github.jcpp.jathenaeum.Scrivere;
import com.github.jcpp.jathenaeum.beans.AddBookForm;
import com.github.jcpp.jathenaeum.db.dao.LibroDAO;
import com.github.jcpp.jathenaeum.db.dao.ScrivereDAO;

/**
 * Add book action.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class AddBookAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String action_target = null;

		Libro book = new Libro();
		AddBookForm uf = (AddBookForm) form;
		if(form != null){
			book.setTitolo(uf.getTitle());
			book.setCopertina(uf.getCover());
			book.setGenere(uf.getGenre());
			book.setCodiceIsbn(uf.getIsbn());
			book.setDescrizione(uf.getDescription());


			try{
				long bookId = LibroDAO.insert(book);
				if(bookId != 0){
					String authors[] = uf.getAuthors();
					Scrivere scrivere;
					for(int i = 0; i < authors.length; i++){
						scrivere = new Scrivere();
						scrivere.setIdAutore(Integer.parseInt(authors[i]));
						scrivere.setIdLibro(bookId);
						ScrivereDAO.insert(scrivere);
					}
					
					action_target = "success";
				}
				
				

			}catch(Exception e){
				action_target = "failed";
			}
		}

		return mapping.findForward(action_target);
	}



}
