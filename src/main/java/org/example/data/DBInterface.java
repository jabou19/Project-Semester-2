package org.example.data;

import org.example.business.credits.Person;
import org.example.business.credits.Credit;
import org.example.business.credits.Production;
import org.example.business.users.User;

import java.sql.*;
import java.util.ArrayList;

public class DBInterface {

    private static Connection connection = null;
    // den fra klassen DBInterface
    private static DBInterface instance = null;

    /**
     * BDInterface constructor.
     * Tries to connect to DB and returns error if not possible
     */
    private DBInterface() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/stp2",
                    "postgres",
                    "abcd1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates new instance if it doesnt exist. Singleton design pattern as there can only exist one connection to DB
     * @return the instance of DB interface
     */
    public static DBInterface getInstance() {
        if (instance == null) {
            instance = new DBInterface();
        }
        return instance;
    }
// fra person klasse
    public int insertPerson(Person person) {// uden ID
        try {
            PreparedStatement insert = connection.prepareStatement("INSERT INTO person (name, email, phone_number) VALUES (?,?,?)");
            insert.setString(1, person.getName());// fra person klasse
            insert.setString(2, person.getEmail());// fra person klasse
            insert.setInt(3, person.getPhoneNumber());// fra person klasse
            insert.execute();
            // Email is unique, so we can find person based on email, when created.
            PreparedStatement queryId = connection.prepareStatement("SELECT id FROM person WHERE email = ?");
            queryId.setString(1, person.getEmail());
            // execute Query returns linked list. GetInt of column 1. Should only be one result from query since email is unique.
            ResultSet resultSet = queryId.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    // fra person klasse
    public void editPerson(Person person) {// med ID
        try {
            PreparedStatement insertEdit = connection.prepareStatement("UPDATE person SET name = ?, email = ?, phone_number = ? WHERE id = ?");
            insertEdit.setString(1, person.getName());// fra person klasse
            insertEdit.setString(2, person.getEmail());// fra person klasse
            insertEdit.setInt(3, person.getPhoneNumber());// fra person klasse
            insertEdit.setInt(4, person.getId());// med ID// fra person klasse
            insertEdit.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    // fra person klasse
    public boolean deletePerson(Person person) {// kun ID

        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM person WHERE id = (?)");
            delete.setInt(1, person.getId());// kun ID// fra person klasse
            return delete.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    // fra produktion klasse
    public int insertProduction(Production production) {
        int productionId = 0;
        // Insert production
        try {
            PreparedStatement insert = connection.prepareStatement("INSERT INTO production (title) VALUES (?)");
            insert.setString(1, production.getTitle());// fra produktion klasse
            insert.execute();
            // Email is unique, so we can find person based on email, when created.
            PreparedStatement queryId = connection.prepareStatement("SELECT id FROM production WHERE title = ?");
            queryId.setString(1, production.getTitle());// fra produktion klasse
            ResultSet resultSet = queryId.executeQuery();
            resultSet.next();
            productionId = resultSet.getInt(1);
                               //klasse metoden
            boolean producerCreated = insertCredit(User.id, productionId, "Producer");
            if (!producerCreated) {
                return 0;
            }
            return productionId;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    // fra produktion klasse
    public void editProduction(Production production) {
        try {
            PreparedStatement insertEdit = connection.prepareStatement("UPDATE production SET title = ? WHERE id = ?");
            insertEdit.setString(1, production.getTitle());// fra produktion klasse
            insertEdit.setInt(2, production.getId());// fra produktion klasse
            insertEdit.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    // fra produktion klasse
    public void deleteProduction(Production production) {
        // Delete cread
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM production WHERE id = (?)");
            delete.setInt(1, production.getId());  // fra produktion klasse
            delete.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
// fra klasse Credit
    public boolean insertCredit(int personId, int productionId, String role) {
        try {
            PreparedStatement insert = connection.prepareStatement("INSERT INTO credit (person_id,production_id,role) VALUES (?,?,?)");
            insert.setInt(1, personId);
            insert.setInt(2, productionId);
            insert.setString(3, role);
            insert.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    // fra klasse Credit
    public boolean editCredit(int personId, int productionId, String role) {
        try {
            PreparedStatement insertEdit = connection.prepareStatement("UPDATE credit SET role = ? WHERE person_id = ? AND production_id = ?");
            insertEdit.setString(1, role);
            insertEdit.setInt(2, personId);
            insertEdit.setInt(3, productionId);
            insertEdit.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    // fra klasse Credit
    public boolean deleteCredit(int personId, int productionId) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM credit WHERE person_id = (?) AND production_id = (?)");
            delete.setInt(1, personId);
            delete.setInt(2, productionId);
            return delete.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
// klasse person
    public ArrayList<Person> getPeople() {
        ArrayList<Person> people = new ArrayList<>();
        try {
            PreparedStatement selectedPeople = connection.prepareStatement("SELECT * FROM person");
            ResultSet set = selectedPeople.executeQuery();
            while (set.next()) {
                Person person = new Person(
                        set.getInt("id"),
                        set.getString("name"),
                        set.getString("email"),
                        set.getInt("phone_number")
                );
                people.add(person);
            }
            return people;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return people;
        }
    }
// klasse produktion
    public ArrayList<Production> getProductions() {
        ArrayList<Production> productions = new ArrayList<>();
        try {
            PreparedStatement selectedProductions = connection.prepareStatement("SELECT * FROM production");
            ResultSet set = selectedProductions.executeQuery();
            while (set.next()) {
                Production production = new Production(
                        set.getInt("id"),
                        set.getString("title")
                );
                productions.add(production);
            }
            return productions;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return productions;
        }
    }
// klasse Credit
    public void loadCredits() {
        ArrayList<Credit> credits = new ArrayList<>();
        try {
            PreparedStatement selectedCredits = connection.prepareStatement("SELECT * FROM credit");
            ResultSet set = selectedCredits.executeQuery();
            while (set.next()) {
                Credit credit = new Credit(
                        set.getInt("person_id"),
                        set.getInt("production_id"),
                        set.getString("role")
                );
                credits.add(credit);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
