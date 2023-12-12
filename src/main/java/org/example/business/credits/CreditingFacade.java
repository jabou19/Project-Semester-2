package org.example.business.credits;

public class CreditingFacade {
// oprindelig
    public static Person addPerson(String name, String email, int phoneNumber){
        return new Person(name, email, phoneNumber);
    }
    // oprindelig
    public static Production addProduction(String title){
        return new Production(title);
    }
    // oprindelig
    public static Credit addCredit(Person person, Production production, String role) {
        return new Credit(person, production, role);
    }
}
