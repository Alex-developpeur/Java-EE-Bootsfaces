package dev.fatum.www.webapp.web;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import dev.fatum.www.consumer.ejb.GroupesBean;
import dev.fatum.www.business.web.util.JsfUtil;
import dev.fatum.www.business.web.util.PageNavigation;
import dev.fatum.www.model.Groupes;

/**
 * @author LEONARD
 */
@Named(value = "groupesController")
@SessionScoped
public class GroupesController implements Serializable {

	private static final String BUNDLE = "bundles.Bundle";
	private static final long serialVersionUID = 8170385117226855466L;

	private Groupes current;
    private DataModel<Groupes> items = null;
    @EJB
    private dev.fatum.www.consumer.ejb.GroupesBean ejbFacade;

    private static final Logger logger = Logger.getLogger(GroupesController.class.getCanonicalName());
    
    public GroupesController() {
    }

    public Groupes getSelected() {
        if (current == null) {
            current = new Groupes();
        }
        return current;
    }

    private GroupesBean getFacade() {
        return ejbFacade;
    }
        
    public PageNavigation prepareList() {
        recreateModel();
        return PageNavigation.LISTE;
    }
    
    public PageNavigation prepareView() {
        current = (Groupes) getItems().getRowData();
        return PageNavigation.VOIR;
    }

    public PageNavigation prepareCreate() {
        current = new Groupes();
        return PageNavigation.CREER;
    }

    private boolean isGroupeDuplicated(Groupes g) {
        return (getFacade().getGroupeByNom(g.getNom()) == null) ? false : true;
    }

    public PageNavigation create() {
        try {
            if (!isGroupeDuplicated(current)) {
                getFacade().create(current);
                JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("GroupeCreated"));
                recreateModel();
                return PageNavigation.LISTE;
            } else {
            	JsfUtil.addErrorMessage(ResourceBundle.getBundle(BUNDLE).getString("DuplicatedGroupeError"));
            	return PageNavigation.CREER;
            }
        } catch (Exception e) {
        	JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("GroupeCreatedError"));
        	return PageNavigation.CREER;
        }
    }
    
    public PageNavigation prepareEdit() {
    	current = (Groupes) getItems().getRowData();
        return PageNavigation.EDITER;
    }
    
    private boolean isNomGroupeDuplicated(Groupes g) {
        return getFacade().checkNom(g);
    }
    
    public PageNavigation update() {
        try {
        	if(isNomGroupeDuplicated(current)) {
	            logger.log(Level.INFO, "Updating utilisateur ID:{0}", current.getId());
	            // password encrypt
	            getFacade().edit(current);
	            JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("GroupeUpdated"));
	            return PageNavigation.VOIR;
        	} else {
        		JsfUtil.addErrorMessage(ResourceBundle.getBundle(BUNDLE).getString("DuplicatedNomGroupeError"));
        		return PageNavigation.EDITER;
        	}
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("PersistenceErrorOccured"));
            return PageNavigation.EDITER;
        }
    }
        
    public PageNavigation destroyInlist() {
    	current = (Groupes) getItems().getRowData();
        return destroy();
    }
    
    public PageNavigation destroy() {
        performDestroy();
        recreateModel();
        JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("GroupeDeleted"));
        return PageNavigation.LISTE;
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("PersistenceErrorOccured"));
        }
    }

    public DataModel<Groupes> getItems() {
        if (items == null) {
        	items = new ListDataModel<Groupes>(getFacade().findAllDesc());
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }
    
    public int getGroupesCount() {
    	return ejbFacade.count();
    }
    
}
