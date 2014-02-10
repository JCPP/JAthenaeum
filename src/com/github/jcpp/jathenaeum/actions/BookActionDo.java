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
import com.github.jcpp.jathenaeum.db.dao.CopyDAO;
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
				if(id != 0){
					String authors[] = uf.getAuthors();
					Writes writes;
					ArrayList<Writes> oldWritesList = WritesDAO.getAllByBookId(id);
					
					ArrayList<Writes> newWrites = getNewWrites(authors, oldWritesList, id);
					ArrayList<Writes> oldWrites = getOldWrites(authors, oldWritesList, id);
					
					
					//Remove deleted authors
					for(int i = 0; i < oldWrites.size(); i++){
						writes = oldWrites.get(i);
						
						System.out.println("Deleting writes with ID: " + writes.getId());
						WritesDAO.delete(writes);
					}
					
					//Add new authors
					for(int i = 0; i < newWrites.size(); i++){
						writes = newWrites.get(i);
						
						System.out.println("Adding writes with ID: " + writes.getId());
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
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		BookForm uf = (BookForm) form;
		int id = Integer.parseInt(request.getParameter("id"));
		
		//ActionErrors actionErrors = uf.validate(mapping, request);
		
		//If there are some errors, redirect to the form page
		/*
		if(!actionErrors.isEmpty()){
			actionTarget = "deleteErrors";
			saveErrors(request, actionErrors); //Save the errors
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}
		*/
		if(form != null){
			try{
				int bookId = (int) BookDAO.delete(id);
				if(bookId != 0){
					Writes writes;
					ArrayList<Writes> oldWritesList = WritesDAO.getAllByBookId(bookId);
					
					
					//Remove deleted authors
					for(int i = 0; i < oldWritesList.size(); i++){
						writes = oldWritesList.get(i);
						
						System.out.println("Deleting writes with ID: " + writes.getId());
						WritesDAO.delete(writes);
					}
					
					actionTarget = "deleteSuccess";
				}
				
				

			}catch(Exception e){
				actionTarget = "deleteFailed";
			}
		}
		
		

		return mapping.findForward(actionTarget);
	}
	
	
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		BookForm uf = (BookForm) form;
		
		//ActionErrors actionErrors = uf.validate(mapping, request);
		
		//If there are some errors, redirect to the form page
		/*
		if(!actionErrors.isEmpty()){
			actionTarget = "deleteErrors";
			saveErrors(request, actionErrors); //Save the errors
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}
		*/
		
		ArrayList<Book> books = new ArrayList<Book>();
		
		if(form != null){
			try{
				books = BookDAO.searchWithAuthors(uf);
				
				actionTarget = "search";
				
				HttpSession session = request.getSession();
	    		session.setAttribute("books", books);
	    		session.setAttribute("form", uf);
				
				ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
				return redirect;

			}catch(Exception e){
				e.printStackTrace();
				actionTarget = "searchFailed";
			}
		}
		
		

		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward manageCopies(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		int id = Integer.parseInt(request.getParameter("id"));
		
		BookForm uf = (BookForm) form;
		
		ActionErrors actionErrors = uf.validateNumberOfCopies(mapping, request);
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "manageCopiesErrors";
			saveErrors(request, actionErrors); //Save the errors
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}
		
		
		if(form != null){
			int numberOfCopies = Integer.parseInt(uf.getNumberOfCopies());
			int actualCopies = CopyDAO.getNumberByBookId(id);
			
			if(actualCopies > numberOfCopies){
				//Remove
				System.out.println("Removing " +  (actualCopies - numberOfCopies) + " copies.");
				
				CopyDAO.multipleDelete(id, actualCopies - numberOfCopies);
			}
			else if(actualCopies < numberOfCopies){
				//Add
				System.out.println("Adding " +  (numberOfCopies - actualCopies) + " copies.");
				
				CopyDAO.multipleInsert(id, numberOfCopies - actualCopies);
			}
			
			actionTarget = "manageCopiesSuccess";
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
	private static ArrayList<Writes> getNewWrites(String[] authorsId, ArrayList<Writes> oldWritesList, int bookId){
		ArrayList<Writes> newWrites = new ArrayList<Writes>();
		
		for(int i = 0; i < authorsId.length; i++){
			boolean isNew = true;
			int writesId = 0;
			
			for(int j = 0; j < oldWritesList.size(); j++){
				if(Integer.parseInt(authorsId[i]) == oldWritesList.get(j).getAuthorId()){
					isNew = false;
				}
				writesId = oldWritesList.get(j).getId();
			}
			if(isNew){
				Writes writes = new Writes();
				writes.setAuthorId(Integer.parseInt(authorsId[i]));
				writes.setBookId(bookId);
				writes.setId(writesId);
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
	private static ArrayList<Writes> getOldWrites(String[] authorsId, ArrayList<Writes> oldWritesList, int bookId){
		ArrayList<Writes> oldWrites = new ArrayList<Writes>();
		
		
		for(int i = 0; i < oldWritesList.size(); i++){
			boolean isOld = true;
			Writes tempWrite = oldWritesList.get(i);
			
			for(int j = 0; j < authorsId.length; j++){
				if(Integer.parseInt(authorsId[j]) == tempWrite.getAuthorId()){
					isOld = false;
				}
			}
			
			if(isOld){
				Writes writes = new Writes();
				writes.setAuthorId(tempWrite.getAuthorId());
				writes.setBookId(bookId);
				writes.setId(tempWrite.getId());
				oldWrites.add(writes);
			}
			
		}
		
		return oldWrites;
	}

}
