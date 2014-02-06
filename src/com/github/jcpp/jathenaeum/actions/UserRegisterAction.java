package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.User;
import com.github.jcpp.jathenaeum.beans.UserRegisterForm;
import com.github.jcpp.jathenaeum.db.dao.UserDAO;
import com.github.jcpp.jathenaeum.utils.Converter;

public class UserRegisterAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action_target = null;
		
		User user = new User();
		//ActionMessages errors_mesg = new ActionMessages();
        UserRegisterForm uf = (UserRegisterForm) form;
        if(form != null){
        	user.setEmail(uf.getEmail());
        	user.setPassword(uf.getPassword());
        	user.setName(uf.getName());
        	user.setSurname(uf.getSurname());
        	
        	if(Converter.checkStringToDate(uf.getBornDate())){
        		user.setBornDate(Converter.fromStringToDate(uf.getBornDate()));
			}
        	
        	
        	try{
        		if(UserDAO.register(user)){
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
