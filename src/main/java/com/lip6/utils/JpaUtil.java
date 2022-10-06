package com.lip6.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	   /** 
     * Singleton de l�EntityManagerFactory de l�application 
     */ 
    private static EntityManagerFactory emf = null; 
 
    /** 
     * Permet de r�cup�rer l�EntityManagerFactory de  
     * l�application tout en le cr�ant s�il n�existe pas 
     *  
     * @return l�EntityManagerFactory unique de l�application 
     */ 
    public static EntityManagerFactory getEmf() { 
        if(emf == null){ 
        	 System.out.println("in 1");
            emf = Persistence.createEntityManagerFactory("projetJPA"); 
            System.out.println("in 2");
        } 
        return emf; 
    } 
 
    /** 
     * Lib�re les ressources et d�truit l�EntityManagerFactory 
     * si jamais il faut le recr�er. 
     */ 
    public static void close(){ 
        if(emf!=null){ 
            emf.close(); 
            emf=null; 
        } 
    } 

}
