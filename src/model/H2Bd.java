package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2Bd {
    private static H2Bd instance = null;
    private Connection connect;
    private Statement statement;
    private Long iD = 1L;
    private int index = 0;

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
//           TO_DO: use select top
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
        List<Person> list= new ArrayList<>() ;
        try {
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:~/test", "admin", "");
            statement = connect.createStatement();
            ResultSet r = statement.executeQuery("SELECT * FROM PERSON ");

            while (r.next()){
                list.add(new Person(r.getLong("ID"),
                        r.getInt("index"),
                        r.getString("fname"),
                        r.getString("lname"),
                        r.getLong("age")));
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
            r.next();
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
            statement.executeUpdate("Update Person set fname='" + person.getFname()+"', " +
                    "lname='"+ person.getLname()+"', " +
                    "age="+ person.getAge()+" " +
                    "Where ID="+person.getId());

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

    public Person search (long id){
        Person person = null;
        try {
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:~/test", "admin", "");
            statement = connect.createStatement();
            ResultSet r = statement.executeQuery("Select * FROM Person WHERE ID =" + id);
            r.next();
            person = new Person(id,r.getInt("index"),r.getString("fname"),r.getString("lname"),r.getLong("age"));

//            person = new Person(id,r.getInt("index"),r.getString("fname"),r.getString("lname"),r.getLong("age"));
            //Person(Long id, int index, String fname, String lname, Long age)


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
}

