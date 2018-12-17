package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
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
            ResultSet r = statement.executeQuery("SELECT MAX(ID) FROM PERSON ");
            r.next();
            if (r.getString(1) != null)
                iD=r.getLong(1);
                    else iD=0L;

//            while (r.next()){
//                iD = r.getLong("ID");
//            }
            person.setId(++iD);
            person.setIndex(++index);
            statement.executeUpdate("insert into Person values(" + iD + "," + person.getIndex()+
                    ",'" + person.getFname() + "','" + person.getLname() + "'," + person.getAge() + ")");
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

    public void export() throws IOException {
        ArrayList<Person> exportList = (ArrayList) read();
        Gson gson = new Gson();
        FileOutputStream fileOutputStream;
        String path = "Persons.txt";
        fileOutputStream = new FileOutputStream(path);

        for(int i=0; i<exportList.size();i++)

        {
//            String text = exportList.get(i).toString();
//            String text = gson.toJson(exportList.get(i));
            String text = exportList.get(i).getId()+","+exportList.get(i).getIndex()+","+exportList.get(i).getFname()+","+exportList.get(i).getLname()+","+exportList.get(i).getAge();
            String text1 = text+"\n";
            byte[] buffer = text1.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        }
        fileOutputStream.close();
    }
    
    public void _import() throws IOException {
//        ArrayList<Person> importList = new ArrayList();
//        String path = "Persons.txt";
//        Gson gson = new Gson();
//        JsonObject json= new JsonObject();
//        FileReader reader = new FileReader(path);
//        BufferedReader buffer = new BufferedReader(reader);
//        while (buffer.readLine() != null) {
//            String p=buffer.readLine();
//
//   //         String s = gson.toJson(buffer.readLine());
//            importList.add(p);
//        }
//        reader.close();

        ArrayList<Person> importList = new ArrayList();
        String path = "Persons.txt";
        FileReader reader = new FileReader(path);
        BufferedReader buffer = new BufferedReader(reader);
        String str;
        while ((str=buffer.readLine()) != null) {
            String[] s = str.split("\n");
            for (int i = 0; i < s.length ; i++) {

                String[] p = s[i].split(",");
                importList.add(new Person(Long.parseLong(p[0]), Integer.parseInt(p[1]), p[2], p[3], Long.parseLong(p[4])));
            }
        }
        reader.close();

    // Delete all data from db
        try {
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:~/test", "admin", "");
            statement = connect.createStatement();
            statement.executeUpdate("DELETE FROM Person");
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

//        add imported Persons to db
        for (int i=0; i < importList.size(); i++){
            create(importList.get(i));
        }

    }
}

