package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.Writes;
import com.github.jcpp.jathenaeum.beans.AddBookForm;
import com.github.jcpp.jathenaeum.db.dao.BookDAO;
import com.github.jcpp.jathenaeum.db.dao.WritesDAO;

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

		Book book = new Book();
		AddBookForm uf = (AddBookForm) form;
		if(form != null){
			book.setTitle(uf.getTitle());
			book.setCover(uf.getCover());
			book.setGenre(uf.getGenre());
			book.setIsbnCode(uf.getIsbn());
			book.setDescription(uf.getDescription());


			try{
				long bookId = BookDAO.insert(book);
				if(bookId != 0){
					String authors[] = uf.getAuthors();
					Writes writes;
					for(int i = 0; i < authors.length; i++){
						writes = new Writes();
						writes.setAuthorId(Integer.parseInt(authors[i]));
						writes.setBookId(bookId);
						WritesDAO.insert(writes);
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
