package model;

import java.util.List;

public interface TableContract {
    void setAllValue(List<Person> list);

    void setValue(Person pers);

    void setSearch(Person pers);

    void setUpdateValue(Person pers);

    void delValue(int id);





}
