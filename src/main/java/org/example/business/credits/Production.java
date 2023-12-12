package org.example.business.credits;

import org.example.data.DBInterface;

import java.util.ArrayList;
import java.util.List;

public class Production {
    private static int idCount = 0;
    private String title;
    private int id;
    public List<Credit> credits;

    public Production(int id, String title) {
        this.title = title;
        this.id = id;
        credits = new ArrayList<>();
    }
    public Production(String title) {
        this.title = title;
        credits = new ArrayList<>();
        this.id = DBInterface.getInstance().insertProduction(this);// kende til DBInterface
    }

    // ############## Get & set ###############
// kende til DBInterface klasse
    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }
    // kende til DBInterface klasse
    public int getId() {
        return id;
    }

    // ############## Object Functions ################
// op
    public void deleteCredit(Credit credit) {
        credits.remove(credit);
    }
    // op
    public void addCredit(Credit credit) {
        credits.add(credit);
    }
    // op
    public void edit(String title) {
        setTitle(title);
        DBInterface.getInstance().editProduction(this);// kende til DBInterface
    }
    // op
    public Credit getCredit(Person person){
        for(Credit credit : credits){
            if(credit.getPerson().getId() == person.getId()){
                return credit;
            }
        }
        return null;
    }

    @Override// op
    public String toString() {
        return "{ production[" + id + "]: {title='" + title + "'} }";
    }
    // op
    public Production delete() {
        DBInterface.getInstance().deleteProduction(this);// kende til DBInterface
        return this;
    }

    //It's possible we need some function to get the credit by the person's id. Consider "public Credit getCredit(Person p)"
}
