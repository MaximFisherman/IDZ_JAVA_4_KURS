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
public class table_grups {
    private String grups_name;
    private int idgr;
    
    public table_grups(String grups_name,int idgr) 
    {
        this.grups_name = grups_name;
        this.idgr = idgr;
    }
 
    public table_grups() {
    }
 
    public String getGrups_name() {
        return grups_name;
    }
 
    public void setGrups_name(String grups_name) {
        this.grups_name = grups_name;
    }
    
     public int getIdgr() {
        return idgr;
    }
 
    public void setIdgr(int idgr) {
        this.idgr = idgr;
    }
}
