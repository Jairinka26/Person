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
        list = new ArrayList<>(1);
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
