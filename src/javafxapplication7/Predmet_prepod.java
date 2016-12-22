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
public class Predmet_prepod {
    
    private String prepod_name;
    private String predmet_name;
   
 
    public Predmet_prepod (String prepod_name,String predmet_name) {
        this.prepod_name = prepod_name;
        this.predmet_name = predmet_name;
    }
 
    public Predmet_prepod() {
    }
 
    public String getPrepod_name() {
        return prepod_name;
    }
 
    public void setPrepod_name(String prepod_name) {
        this.prepod_name = prepod_name;
    }
 
    public String getPredmet_name() {
        return predmet_name;
    }
 
    public void setPredmet_name(String predmet_name) {
        this.predmet_name = predmet_name;
    }
}
