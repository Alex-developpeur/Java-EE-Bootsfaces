package dev.fatum.www.business.web.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author LEONARD
 */
@FacesValidator("changePasswordAction")
public class ChangePasswordAction implements Serializable, Validator<Object> {

	private static final String BUNDLE = "bundles.Bundle";
	private static final long serialVersionUID = -4085811641572153875L;
	
	private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    
	public ChangePasswordAction() {
	}
	
	public boolean changePassword(String mdp) {
		if(this.getOldPassword() == null) {
			return false;
		} else if(!PasswordStorage.verifyPasswordBool(this.getOldPassword(), mdp)) {
			JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle(BUNDLE, "WrongPassword"));
			return false;
		}
		if(this.getNewPassword() == null || this.getConfirmPassword() == null)
			return false;
		if(this.getNewPassword().length() > 100 || this.getNewPassword().length() < 6)
			return false;
		if(this.getConfirmPassword().equals(this.getNewPassword())) {
			this.setNewPassword(PasswordStorage.createHash(this.getNewPassword()));
			return true;
		}
        return false;
    }
		
	@Override
    public void validate(FacesContext context, UIComponent component, Object confirmPassword) throws ValidatorException {
        Object newPassword = component.getAttributes().get("newPassword");
		
        if (confirmPassword == null || newPassword == null) {
            return; // Let required="true" handle.
        }

        if (!confirmPassword.equals(newPassword)) {
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, JsfUtil.getStringFromBundle(BUNDLE, "BadConfirmPassword"));
        	throw new ValidatorException(message);
        }
    }

	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
 
}
