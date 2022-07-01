package dev.fatum.www.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.model.Utilisateur;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	
    	ApplicationContext vApplicationContext
		= new ClassPathXmlApplicationContext("classpath:/bootstrapContext.xml");
    	
    	// Il est possible de récupérer un bean dans ce contexte :
	    ManagerFactory vManagerFactory
	        = vApplicationContext.getBean("managerFactory", ManagerFactory.class);
	    
	    int nbProfil = vManagerFactory.getUtilisateurManager().getCountProfil();

        System.out.println( "Hello World ! " +  nbProfil);
        
        Utilisateur vUtilisateur = new Utilisateur();
        vUtilisateur.setMdp("mdp");
        vUtilisateur.setEmail("email");
        nbProfil = vManagerFactory.getUtilisateurManager().createUser(vUtilisateur);
        		
        System.out.println( "Création utilisateur : " +  nbProfil);
    }
}
