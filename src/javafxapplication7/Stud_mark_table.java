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
    public class Stud_mark_table {
 
    private String fio;
    private int mark;
    private String predmet_name;
    private String prepod_fio;
            
    

 
    public Stud_mark_table(String fio,int mark,String predmet_name,String prepod_fio) {
        this.fio = fio;
        this.mark = mark;
        this.predmet_name = predmet_name;
        this.prepod_fio = prepod_fio;
    }
 
    public Stud_mark_table() {
    }
 
    public String getFio() {
        return fio;
    }
 
    public void setFio(String fio) {
        this.fio = fio;
    }
 
    public int getMark() {
        return mark;
    }
 
    public void setMark(int mark) {
        this.mark = mark;
    }
    
    public String getPredmet() {
        return predmet_name;
    }
 
    public void setPredmet(String predmet_name) {
        this.predmet_name = predmet_name;
    }
    
    public String getTeacher() {
        return prepod_fio;
    }
 
    public void setTeacher(String prepod_fio) {
        this.prepod_fio = prepod_fio;
    }
}

