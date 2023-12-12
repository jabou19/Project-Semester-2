package org.example.business.credits;

import org.example.business.users.User;
import org.example.data.DBInterface;

public class Credit {

    private final Person person;
    private final Production production;
    private String role;


    public Credit(Person person, Production production, String role) {
        this.person = person;
        this.production = production;
        this.role = role;

        person.addCredit(this);// kende til person klasse
        production.addCredit(this);// kende til produktion klasse
// kende til DBInterface klasse
        DBInterface.getInstance().insertCredit(person.getId(), production.getId(), role);  // kende til DBInterface klasse
    }

    public Credit(int personId, int productionId, String role) {
        this.person = User.getPersonByID(personId);
        this.production = User.getProductionByID(productionId);
        this.role = role;

        person.addCredit(this);
        production.addCredit(this);
    }

    // ############### Get & Set ####################

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public Production getProduction() {
        return production;
    }

    // ################ Object Functions

    public void delete() {
        person.deleteCredit(this);// kende til person klasse
        production.deleteCredit(this);// kende til produktion klasse
        // kende til DBInterface klasse
        DBInterface.getInstance().deleteCredit(person.getId(), production.getId());
    }

    public void edit(String role) {
        setRole(role);
        // kende til DBInterface klasse
        DBInterface.getInstance().editCredit(person.getId(), production.getId(), role);
    }

    @Override
    public String toString() {// op
        return "{ " +
                    "Credit{" +
                        " person[" + person.getId() + "]: {" +
                            " name=\"" + person.getName() + "\" " +
                        " }," +
                        " production[" + production.getId() + "]: {" +
                            " title=\"" + production.getTitle() + "\"" +
                        " }," +
                        " role=\"" + role + "\" " +
                    "} " +
                "}";
    }
}
