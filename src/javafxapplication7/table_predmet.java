/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

/**
 *
 * @author Maks_
 */
public class table_predmet {
    private int idpredmet;
    private String predmet_name;
    
    public table_predmet(int idpredmet,String predmet_name) 
    {
        this.idpredmet = idpredmet;
        this.predmet_name = predmet_name;
    }
 
    public table_predmet() {
    }
 
    public String getPredmet_name() {
        return predmet_name;
    }
 
    public void setPredmet_name(String predmet_name) {
        this.predmet_name = predmet_name;
    }
    
     public int getIdpredmet() {
        return idpredmet;
    }
 
    public void setIdpredmet(int idpredmet) {
        this.idpredmet = idpredmet;
    }
}
