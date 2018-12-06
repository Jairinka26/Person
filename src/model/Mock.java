package model;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class Mock {
    private static Mock instance = null;
    private Long iD = 1L;
    private int index = 0;
    private List<Person> list;

    private Mock() {
//        list = new ArrayList<>();
//        list.add(new Person(1L,0,"ola","kol",23L));
//        index++;
//        iD++;
//        list.add(new Person(2L,1,"ola1","kol1",23L));
//        index++;
//        iD++;
//        list.add(new Person(3L,2,"ola2","kol2",23L));
//        index++;
//        iD++;
//        list.add(new Person(4L,3,"ola3","kol3",23L));
//        index++;
//        iD++;
//        list.add(new Person(5L,4,"ola4","kol4",23L));
//        index++;
//        iD++;
//        list.add(new Person(6L,5,"ola5","kol5",23L));
//        index++;
//        iD++;
//        list.add(new Person(7L,6,"ola6","kol6",23L));
//        index++;
//        iD++;
//        list.add(new Person(8L,7,"ola7","kol7",23L));
//        index++;
//        iD++;
//        list.add(new Person(9L,8,"ola8","kol8",23L));
//        index++;
//        iD++;
//        list.add(new Person(10L,9,"ola9","kol9",23L));
//        index++;
//        iD++;

    }

    public static synchronized Mock getInstance() {
        if (instance == null) {
            instance = new Mock();

        }
        return instance;
    }

    @NotNull
    public void create(Person person) {
        iD = (long)index + 1;
        person.setId(iD);
        person.setIndex(index);
        list.add(person);
//        list.add(index, person);
        index++;
    }

    public List<Person> read() {
        return list != null ? list : new ArrayList<>();
    }

    public Person update(long id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                if (i != 0) {
                    list.set(i, list.get(i));
                }
                return list.get(i);
            }
        }
        return null;
    }

    public Person updateUI(Person person) {
        list.get(person.getIndex()).setFname(person.getFname());
        list.get(person.getIndex()).setLname(person.getLname());
        list.get(person.getIndex()).setAge(person.getAge());
        return list.get(person.getIndex());
    }

    public void delete(long id) {
        boolean isHasId=false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                isHasId=true;
            }
        }
     //   if (isHasId=false)
    }
}
