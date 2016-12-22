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
public class Telefone {
    
    private String fio_stud;
    private String grups_name;
    private int telefone;
    

 
    public Telefone(String fio_stud,String grups_name,int telefone) {
        this.fio_stud = fio_stud;
        this.grups_name = grups_name;
        this.telefone = telefone;
    }
 
    public Telefone() {
    }
 
    public String getFio() {
        return fio_stud;
    }
 
    public void setFio(String fio_stud) {
        this.fio_stud = fio_stud;
    }
 
    public String getGrups_name() {
        return grups_name;
    }
 
    public void setGrups_name(String grups_name) {
        this.grups_name = grups_name;
    }
    
     public int getTel() {
        return telefone;
    }
 
    public void setTel(int telefone) {
        this.telefone = telefone;
    }
}
