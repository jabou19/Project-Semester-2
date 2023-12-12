package org.example.business.credits;

import org.example.data.DBInterface;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private static int idCount = 0;
    private int id;
    private String name;
    private String email;
    private int phoneNumber;

    private List<Credit> credits;

    public Person(int id, String name, String email, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        credits = new ArrayList<>();
    }

    public Person(String name, String email, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        credits = new ArrayList<>();
        this.id = DBInterface.getInstance().insertPerson(this);// kende til DBInterface
    }

    // ################### Get & Set ###################
// kende til DBInterface
    public int getId() {
        return id;
    }
// kende til DBInterface
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // kende til DBInterface
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // kende til DBInterface
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // #################### Object Functions ###################
// op
    public void edit(String name, String email, int phoneNumber){
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        DBInterface.getInstance().editPerson(this);
    }
    // op
    public Person delete() {
        DBInterface.getInstance().deletePerson(this);
        return this;
    }
    // op
    public void addCredit(Credit credit){
        credits.add(credit);
    }
    // op
    public void deleteCredit(Credit credit){
        credits.remove(credit);
    }

    public Credit getCredit(Production production){
        for(Credit credit : credits){
            if(credit.getPerson().getId() == production.getId()){
                return credit;
            }
        }
        return null;
    }

    @Override
// op
    public String toString() {
        return "{ Person[" + id +
                "]: { name='" + name +
                "', mail='" + email +
                "', phone='" + phoneNumber + "' } }";
    }
}
