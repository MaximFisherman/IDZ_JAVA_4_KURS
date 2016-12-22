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
public class table_marks {
    private int nzach;
    private int idprepod;
    private int idpredmet;
    private int marks;
    private String data;

 
    public table_marks(int nzach,int idprepod, int idpredmet,int marks, String data) 
    {
        this.nzach = nzach;
        this.idprepod = idprepod;
        this.marks = marks;
        this.idpredmet = idpredmet;
        this.data = data;
    }
 
    public table_marks() {
    }
 
    public int getNzach() {
        return nzach;
    }
 
    public void setNzach(int znach) {
        this.nzach = nzach;
    }
    
     public int getIdprepod() {
        return idprepod;
    }
 
    public void setIdprepod(int idgr) {
        this.idprepod = idprepod;
    }
    
     public int getIdpredmet() {
        return idpredmet;
    }
 
    public void setIdpredemet(int telefone) {
        this.idpredmet = idpredmet;
    }
    
    public int getMarks() {
        return marks;
    }
 
    public void setMarks(int marks) {
        this.marks = marks;
    }
    
     public String getData() {
        return data;
    }
 
    public void setData(String data) {
        this.data = data;
    }
}
