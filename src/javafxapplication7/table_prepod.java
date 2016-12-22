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
public class table_prepod {
    private int idprepod;
    private String prepod_fio;
    
    public table_prepod(int idprepod,String prepod_fio) 
    {
        this.idprepod = idprepod;
        this.prepod_fio = prepod_fio;
    }
 
    public table_prepod() {
    }
 
    public String getPrepod_fio() {
        return prepod_fio;
    }
 
    public void setPrepod_fio(String prepod_fio) {
        this.prepod_fio = prepod_fio;
    }
    
     public int getIdprepod() {
        return idprepod;
    }
 
    public void setIdprepod(int idprepod) {
        this.idprepod = idprepod;
    }
}
