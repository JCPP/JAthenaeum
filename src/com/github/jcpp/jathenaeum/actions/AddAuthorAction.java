package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.Author;
import com.github.jcpp.jathenaeum.beans.AddAuthorForm;
import com.github.jcpp.jathenaeum.db.dao.AuthorDAO;
import com.github.jcpp.jathenaeum.utils.Converter;

public class AddAuthorAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		//boolean action_perform = false;
		String action_target = null;

		Author author = new Author();
		//ActionMessages errors_mesg = new ActionMessages();
		AddAuthorForm uf = (AddAuthorForm) form;
		if(form != null){
			author.setName(uf.getName());
			author.setSurname(uf.getSurname());
			author.setPhoto(uf.getPhoto());
			author.setBornDate(Converter.fromStringToDate(uf.getBornDate()));
			author.setBiography(uf.getBiography());


			try{
				if(AuthorDAO.insert(author)){
					action_target = "success";
				}

			}catch(Exception e){
				action_target = "failed";
				//throw new RegistrationException();
			}
		}

		return mapping.findForward(action_target);
	}



}
