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
public class table_stud {

    private String fio;
    private String birthday;
    private int telefone;
    private int idgr;
    private int nzach;

 
    public table_stud(String fio,int idgr, int telefone,String birthday,int nzach ) 
    {
        this.fio = fio;
        this.idgr = idgr;
        this.telefone = telefone;
        this.birthday = birthday;
        this.nzach = nzach;
    }
 
    public table_stud() {
    }
 
    public String getFio() {
        return fio;
    }
 
    public void setFio(String fio_stud) {
        this.fio = fio;
    }
    
     public int getIdgr() {
        return idgr;
    }
 
    public void setIdgr(int idgr) {
        this.idgr = idgr;
    }
    
     public int getTelefone() {
        return telefone;
    }
 
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    
    public String getBirthday() {
        return birthday;
    }
 
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
     public int getNzach() {
        return nzach;
    }
 
    public void setNzach(int nzach) {
        this.nzach = nzach;
    }
}
 




