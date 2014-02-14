package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.User;
import com.github.jcpp.jathenaeum.beans.UserForm;
import com.github.jcpp.jathenaeum.db.dao.UserDAO;
import com.github.jcpp.jathenaeum.utils.Converter;
import com.github.jcpp.jathenaeum.utils.Redirector;
import com.github.jcpp.jathenaeum.utils.Validator;

public class UserRegisterAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionTarget = null;
		
		User user = new User();
		//ActionMessages errors_mesg = new ActionMessages();
        UserForm uf = (UserForm) form;
        if(form != null){
        	user.setEmail(uf.getEmail());
        	user.setPassword(uf.getPassword());
        	user.setName(uf.getName());
        	user.setSurname(uf.getSurname());
        	
        	if(Validator.isValidDate(uf.getBornDate())){
        		user.setBornDate(Converter.fromStringToDate(uf.getBornDate()));
			}
        	
        	
        	try{
        		if(UserDAO.register(user)){
        			actionTarget = "success";
        		}
        		
        		//Auto login
        		HttpSession session = request.getSession();
        		session.setAttribute("user", user);
        		
        	}catch(Exception e){
        		actionTarget = "failed";
        		//throw new RegistrationException();
        	}
        }

		return mapping.findForward(actionTarget);
	}

	
	
}
