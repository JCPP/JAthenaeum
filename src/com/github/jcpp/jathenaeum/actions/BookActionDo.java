/**
 * 
 */
package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.Writes;
import com.github.jcpp.jathenaeum.beans.BookForm;
import com.github.jcpp.jathenaeum.db.dao.BookDAO;
import com.github.jcpp.jathenaeum.db.dao.WritesDAO;

/**
 * 
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class BookActionDo extends DispatchAction {
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String action_target = null;
		
		Book book = new Book();
		BookForm uf = (BookForm) form;
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
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String action_target = null;
		
		Book book = new Book();
		BookForm uf = (BookForm) form;
		if(form != null){
			book.setTitle(uf.getTitle());
			book.setCover(uf.getCover());
			book.setGenre(uf.getGenre());
			book.setIsbnCode(uf.getIsbn());
			book.setDescription(uf.getDescription());


			try{
				long bookId = BookDAO.update(book);
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
