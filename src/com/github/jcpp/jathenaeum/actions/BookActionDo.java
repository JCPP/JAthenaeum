/**
 * 
 */
package com.github.jcpp.jathenaeum.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
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
		String actionTarget = null;
		
		Book book = new Book();
		BookForm uf = (BookForm) form;
		
		ActionErrors actionErrors = uf.validate(mapping, request);
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "addErrors";
			saveErrors(request, actionErrors); //Save the errors
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			return redirect;
		}
		
		
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
					
					actionTarget = "addSuccess";
				}
				
				

			}catch(Exception e){
				actionTarget = "addFailed";
			}
		}

		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		Book book = new Book();
		BookForm uf = (BookForm) form;
		int id = Integer.parseInt(request.getParameter("id"));
		
		ActionErrors actionErrors = uf.validate(mapping, request);
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "editErrors";
			saveErrors(request, actionErrors); //Save the errors
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}

		if(form != null){
			book.setId(id);
			book.setTitle(uf.getTitle());
			book.setCover(uf.getCover());
			book.setGenre(uf.getGenre());
			book.setIsbnCode(uf.getIsbn());
			book.setDescription(uf.getDescription());


			try{
				int bookId = (int) BookDAO.update(book);
				if(bookId != 0){
					String authors[] = uf.getAuthors();
					Writes writes;
					ArrayList<Writes> oldWritesList = WritesDAO.getAllByBookId(bookId);
					
					ArrayList<Writes> newWrites = getNewAuthors(authors, oldWritesList, bookId);
					ArrayList<Writes> oldWrites = getOldAuthors(authors, oldWritesList, bookId);
					
					
					//Remove deleted authors
					for(int i = 0; i < oldWrites.size(); i++){
						writes = oldWrites.get(i);
						
						System.out.println("Deleting writes with Author ID: " + writes.getAuthorId());
						WritesDAO.delete(writes);
					}
					
					//Add new authors
					for(int i = 0; i < newWrites.size(); i++){
						writes = newWrites.get(i);
						
						System.out.println("Adding writes with Author ID: " + writes.getAuthorId());
						WritesDAO.insert(writes);
					}
					
					actionTarget = "editSuccess";
				}
				
				

			}catch(Exception e){
				actionTarget = "editFailed";
			}
		}
		
		

		return mapping.findForward(actionTarget);
	}
	
	
	/**
	 * Get all the new authors (those to insert). 
	 * @param authorsId the array with all the authors added from the user.
	 * @param oldWritesList all the old elements.
	 * @param bookId the Book ID.
	 * @return The list with all the authors to delete.
	 */
	private static ArrayList<Writes> getNewAuthors(String[] authorsId, ArrayList<Writes> oldWritesList, int bookId){
		ArrayList<Writes> newWrites = new ArrayList<Writes>();
		
		for(int i = 0; i < authorsId.length; i++){
			boolean isNew = true;
			
			for(int j = 0; j < oldWritesList.size(); j++){
				if(Integer.parseInt(authorsId[i]) == oldWritesList.get(j).getAuthorId()){
					isNew = false;
				}
			}
			if(isNew){
				Writes writes = new Writes();
				writes.setAuthorId(Integer.parseInt(authorsId[i]));
				writes.setBookId(bookId);
				newWrites.add(writes);
			}
			
		}
		
		return newWrites;
	}
	
	
	/**
	 * Get all the old authors (those to delete). 
	 * @param authorsId the array with all the authors added from the user.
	 * @param oldWritesList all the old elements.
	 * @param bookId the Book ID.
	 * @return The list with all the authors to delete.
	 */
	private static ArrayList<Writes> getOldAuthors(String[] authorsId, ArrayList<Writes> oldWritesList, int bookId){
		ArrayList<Writes> oldWrites = new ArrayList<Writes>();
		
		
		for(int i = 0; i < oldWritesList.size(); i++){
			boolean isOld = true;
			
			for(int j = 0; j < authorsId.length; j++){
				if(Integer.parseInt(authorsId[j]) == oldWritesList.get(i).getAuthorId()){
					isOld = false;
				}
			}
			
			if(isOld){
				Writes writes = new Writes();
				writes.setAuthorId(oldWritesList.get(i).getAuthorId());
				writes.setBookId(bookId);
				oldWrites.add(writes);
			}
			
		}
		
		return oldWrites;
	}

}
