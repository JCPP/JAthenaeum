package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.Utente;
import com.github.jcpp.jathenaeum.beans.UserRegisterForm;
import com.github.jcpp.jathenaeum.db.dao.UtenteDAO;
import com.github.jcpp.jathenaeum.utils.Converter;

public class UserRegisterAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action_target = null;
		
		Utente user = new Utente();
		//ActionMessages errors_mesg = new ActionMessages();
        UserRegisterForm uf = (UserRegisterForm) form;
        if(form != null){
        	user.setEmail(uf.getEmail());
        	user.setPassword(uf.getPassword());
        	user.setNome(uf.getName());
        	user.setCognome(uf.getSurname());
        	user.setDataNascita(Converter.fromStringToDate(uf.getBornDate()));
        	
        	
        	try{
        		if(UtenteDAO.register(user)){
        			action_target = "success";
        		}
        		
        		HttpSession session = request.getSession();
        		session.setAttribute("user", user);
        		
        	}catch(Exception e){
        		action_target = "failed";
        		//throw new RegistrationException();
        	}
        }

		return mapping.findForward(action_target);
	}

	
	
}
