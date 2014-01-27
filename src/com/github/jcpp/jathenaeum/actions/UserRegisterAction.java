package com.github.jcpp.jathenaeum.actions;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.Utente;
import com.github.jcpp.jathenaeum.beans.UserRegisterForm;
import com.github.jcpp.jathenaeum.db.dao.UtenteDAO;

public class UserRegisterAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//boolean action_perform = false;
		String action_target = null;
		Random rnd = new Random();
		UtenteDAO userDao = new UtenteDAO();
		
		Utente user = new Utente();
		//ActionMessages errors_mesg = new ActionMessages();
        UserRegisterForm uf = (UserRegisterForm) form;
        if(form != null){
        	user.setEmail(uf.getEmail());
        	user.setPassword(uf.getPassword());
        	user.setNome(uf.getName());
        	user.setCognome(uf.getSurname());
        	user.setDataNascita(uf.getBornDate());
        	user.setNumeroTessera(rnd.nextInt(999999)+1);
        	
        	
        	try{
        		if(userDao.register(user)){
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
