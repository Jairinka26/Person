package model;

import java.sql.*;
import java.util.List;

public class H2Bd {
    private static H2Bd instance = null;
    private Connection connect;
    private Statement statement;
    private Long iD = 0L;
    private int index = 0;
    private List<Person> list;

    private H2Bd() {
    }

    public static synchronized H2Bd getInstance() {
        if (instance == null) {
            instance = new H2Bd();
        }
        return instance;
    }
//CREATE TABLE PERSON (ID INT PRIMARY KEY, index INT, fname VARCHAR(255),  lname VARCHAR(255), age INT);
    public void create(Person person) {

        try {
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:~/test", "admin", "");
            statement = connect.createStatement();
//            ResultSet r = statement.executeQuery("SELECT COUNT(*) FROM PERSON ");
            ResultSet r = statement.executeQuery("SELECT * FROM PERSON ");

            while (r.next()){
                iD = r.getLong("ID");
            }
            person.setId(++iD);
            person.setIndex(++index);
            statement.executeUpdate("insert into Person values(" + person.getId() + "," + person.getIndex()+
                    ",'" + person.getFname() + "','" + person.getFname() + "'," + person.getAge() + ")");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null || statement != null) {
                    statement.close();
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Person> read() {
        List<Person> list=null;
        try {
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:~/test", "admin", "");
            statement = connect.createStatement();
//            ResultSet r = statement.executeQuery("SELECT COUNT(*) FROM PERSON ");
            ResultSet r = statement.executeQuery("SELECT * FROM PERSON ");

            while (r.next()){
                list.add(new Person(r.getLong("ID"),r.getInt("index"),r.getString("fname"),r.getString("lname"),r.getLong("age")));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null || statement != null) {
                    statement.close();
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Person update(long ID) {
        Person person = null;
        try {
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:~/test", "admin", "");
            statement = connect.createStatement();
            ResultSet r = statement.executeQuery("Select * FROM Person WHERE ID =" + ID);
//            ResultSet r = statement.executeQuery("Select * FROM Person WHERE ID=3" );
            r.next();
            String name = r.getString("FNAME"); // for debug
            person = new Person(ID,r.getInt("index"),r.getString("fname"),r.getString("lname"),r.getLong("age"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null || statement != null) {
                    statement.close();
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return person;
    }

    public Person updateUI(Person person) {
        try {
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:~/test", "admin", "");
            statement = connect.createStatement();
            statement.executeUpdate("Update Person set fname='" + person.getFname()+"', lname='"+ person.getLname()+"', age="+ person.getAge()+" Where ID="+person.getId());
//            ResultSet r = statement.executeQuery("Select * FROM Person WHERE ID=3" );
//            xt();
//            String name = r.getString("FNAME"); // for debug
//            person = new Person(ID,r.getInt("index"),r.getString("fname"),r.getString("lname"),r.getLong("age"));
//            r.ne
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null || statement != null) {
                    statement.close();
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
//        list.get(person.getIndex()).setFname(person.getFname());
//        list.get(person.getIndex()).setLname(person.getLname());
//        list.get(person.getIndex()).setAge(person.getAge());
//        return list.get(person.getIndex());
        return person;
    }

    public void delete(long iD) {
        try {
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:~/test", "admin", "");
            statement = connect.createStatement();
            statement.executeUpdate("DELETE FROM Person WHERE ID =" + iD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null || statement != null) {
                    statement.close();
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

