package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class H2Bd {
    private static H2Bd instance = null;
    private Connection conect;
    private Statement statement;
    private Long iD = 0L;
    private H2Bd() {
    }

    public static synchronized H2Bd getInstance() {
        if (instance == null) {
            instance = new H2Bd();
        }
        return instance;
    }

    public void create(Person person) {
        person.setId(++iD);
        try {
            Class.forName("org.h2.Driver");
            conect = DriverManager.getConnection("jdbc:h2:~/test", "admin", "");
            statement = conect.createStatement();
            statement.executeUpdate("insert into Person values(" + person.getId() +
                    ",'" + person.getFname() + "','" + person.getFname() + "'," + person.getAge() + ")");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conect != null || statement != null) {
                    statement.close();
                    conect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Person> read() {
        return null;
    }

    public void update() {

    }

    public void delete() {

    }
}

