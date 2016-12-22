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
    public class User {
 
    private String fio_stud;
    private String grups_name;
    

 
    public User(String grups_name,String fio_stud) {
        this.fio_stud = fio_stud;
        this.grups_name = grups_name;
    }
 
    public User() {
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
 
}

