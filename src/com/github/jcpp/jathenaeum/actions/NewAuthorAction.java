package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.Autore;
import com.github.jcpp.jathenaeum.beans.NewAuthorForm;
import com.github.jcpp.jathenaeum.db.dao.AutoreDAO;

public class NewAuthorAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		//boolean action_perform = false;
		String action_target = null;

		Autore autore = new Autore();
		//ActionMessages errors_mesg = new ActionMessages();
		NewAuthorForm uf = (NewAuthorForm) form;
		if(form != null){
			autore.setNome(uf.getNome());
			autore.setCognome(uf.getCognome());
			autore.setFoto(uf.getFoto());
			autore.setDataNascita(uf.getData());
			autore.setBiografia(uf.getBiografia());


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
