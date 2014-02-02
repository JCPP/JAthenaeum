package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.Autore;
import com.github.jcpp.jathenaeum.beans.AddAuthorForm;
import com.github.jcpp.jathenaeum.db.dao.AutoreDAO;
import com.github.jcpp.jathenaeum.utils.Converter;

public class AddAuthorAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		//boolean action_perform = false;
		String action_target = null;

		Autore autore = new Autore();
		//ActionMessages errors_mesg = new ActionMessages();
		AddAuthorForm uf = (AddAuthorForm) form;
		if(form != null){
			autore.setNome(uf.getName());
			autore.setCognome(uf.getSurname());
			autore.setFoto(uf.getPhoto());
			autore.setDataNascita(Converter.fromStringToDate(uf.getBornDate()));
			autore.setBiografia(uf.getBiography());


			try{
				if(AutoreDAO.register(autore)){
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
