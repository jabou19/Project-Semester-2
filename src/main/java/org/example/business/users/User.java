package org.example.business.users;

import org.example.business.credits.Credit;
import org.example.business.credits.CreditingFacade;
import org.example.business.credits.Person;
import org.example.business.credits.Production;
import org.example.business.notifikation.Notifier;
import org.example.data.DBInterface;

import java.util.List;

public class User {
    //                klasse
    private static List<Person> people;
    private static List<Production> productions;
    private static Notifier notifier;

    public static int id = 0;
    private int accessTier;


    // Setup Function
    public static void onLoad(int id) {
        User.id = id;
        people = DBInterface.getInstance().getPeople();// kende til DBInterface klasse
        productions = DBInterface.getInstance().getProductions();// kende til DBInterface klasse
        DBInterface.getInstance().loadCredits();// kende til DBInterface klasse
        notifier = Notifier.getInstance();

        people.forEach(e -> System.out.println(e.toString()));
        productions.forEach(e-> System.out.println(e.toString()));
    }

    public static String[] loadPerson(int id) {
        Person person = getPersonByID(id);
        return new String[] {
                String.valueOf(person.getId()),
                person.getName(),
                person.getEmail(),
                String.valueOf(person.getPhoneNumber())
        };
    }

    public static String[] loadProduction(int id) {
        Production production = getProductionByID(id);
        assert production != null;
        return new String[] {
                String.valueOf(production.getId()),
                production.getTitle()
        };
    }


    // ################### Get & Set ##################

    public int getAccessTier() {
        return accessTier;
    }

    public void setAccessTier(int accessTier) {
        this.accessTier = accessTier;
    }

    // ################### Member Functions ###################
      // fra GUI
    public static void addPerson(String name, String email, int phoneNumber) {
        // typen person klasse    kende til CreditingFacade
        Person person = CreditingFacade.addPerson(name, email, phoneNumber);
        people.add(person);//kende til person klasse
        System.out.println("New person:\n" + person);
        notifier.notifyNewPerson(person);//kende til Notifier klasse
    }
    // fra GUI
    public static void addProduction(String title){
        //typen produktion klasse    kende til CreditingFacade
        Production production = CreditingFacade.addProduction(title);
        productions.add(production);//kende til produktion klasse
        System.out.println("New production:\n" + production);
        notifier.notifyNewProduction(production);//kende til Notifier klasse
    }
    // fra GUI
    public static void addCredit(String personID, String productionID, String role) {
                             //klasse metoden
        Person person = getPersonByID(Integer.parseInt(personID));
                                //klasse metoden
        Production production = getProductionByID(Integer.parseInt(productionID));
        assert production != null && person != null : "Either production or person is null";
        //typen Cridit klasse    kende til CreditingFacade
        Credit credit = CreditingFacade.addCredit(person,production,role);
        System.out.println("New credit:\n" + credit);
        notifier.notifyNewCredit(credit);//kende til Notifier klasse
    }
    // fra GUI
    public static void editPerson(String id, String name, String email, int phoneNumber){
                        //klasse metoden
        Person person = getPersonByID(Integer.parseInt(id));
        assert person != null : "person is null";
        String before = person.toString();// kende til person klasse
        person.edit(name,email,phoneNumber);// kende til person klasse
        System.out.println("Edited person from-to:\n" + before + "\n" + person);
        notifier.notifyEditPerson(before, person);//kende til Notifier klasse
    }
    // fra GUI
    public static void editProduction(String id, String title){
                          //klasse metoden
        Production production = getProductionByID(Integer.parseInt(id));
        assert production != null : "production is null";
        String before = production.toString();// kende til produktion klasse
        production.edit(title);// kende til produktion klasse
        System.out.println("Edited production from-to:\n" + before + "\n" + production);
        notifier.notifyEditProduction(before, production);//kende til Notifier klasse
    }
    // fra GUI
    public static void editCredit(String personID, String productionID, String role) {
        Production production = getProductionByID(Integer.parseInt(productionID)); //klasse metoden
        Person person = getPersonByID(Integer.parseInt(personID)); //klasse metoden
        assert production != null && person != null : "Either production or person is null";
        Credit credit = production.getCredit(person);// kende til produktion klasse
        String before = credit.toString();// kende til kredit klasse
        credit.edit(role);
        System.out.println("Edited credit from-to:\n" + before + "\n" + credit);
        notifier.notifyEditCredit(before,credit);//kende til Notifier klasse
    }
    // fra GUI
    public static void deletePerson(String id){
        Person person = getPersonByID(Integer.parseInt(id));// klasse metoden
        assert person != null : "person is null";
        System.out.println("Deleting:\n" + person);
        people.remove(person.delete());// kende til person klasse
        notifier.notifyDeletePerson(person);//kende til Notifier klasse
    }
    // fra GUI
    public static void deleteProduction(String id) {
        Production production = getProductionByID(Integer.parseInt(id));// klasse metoden
        assert production != null : "production is null";
        System.out.println("Deleting:\n" + production);
        notifier.notifyDeleteProduction(production);//kende til Notifier klasse
        productions.remove(production.delete());
    }
    // fra GUI
    public static void deleteCredit(String personID, String productionId){
        Person person = getPersonByID(Integer.parseInt(personID));// klasse metoden
        Production production = getProductionByID(Integer.parseInt(productionId));// klasse metoden
        assert production != null && person != null : "Either production or person is null";
        Credit credit = production.getCredit(person);// kende til produktion klasse
        System.out.println("Deleting:\n" + credit);
        notifier.notifyDeleteCredit(credit);//kende til Notifier klasse
        credit.delete();
    }

    //############# Private Functions ##############

    public static Person getPersonByID(int id) {
        for (Person person : people) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public static Production getProductionByID(int id) {
        for (Production production : productions) {
            if (production.getId() == id) {
                return production;
            }
        }
        return null;
    }
}
