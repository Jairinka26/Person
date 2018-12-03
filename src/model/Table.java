package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Объект Таблица, с ее помощью обновляем данные в даблице, и формируем ее.
 */

public class Table extends AbstractTableModel implements TableContract {
    //Тут храним что будем отображать в таблице
    private List<Person> listPerson;

    //В конструкторе инициализируем массив
    public Table() {
        listPerson = new ArrayList<>();
    }

    //Длина строк в таблице
    @Override
    public int getRowCount() {
        return listPerson.size();
    }

    //Формеруем название столбцев
    @Override
    public String getColumnName(int column) {
        String[] columnName = {"id","Index", "FName", "LName", "Age"};
        return columnName[column];
    }

    //длина столбцев
    @Override
    public int getColumnCount() {
        return 5;
    }

    //заполняем столбцы после метода обновления
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listPerson.get(rowIndex).getId();
            case 1:
                return listPerson.get(rowIndex).getIndex();
            case 2:
                return listPerson.get(rowIndex).getFname();
            case 3:
                return listPerson.get(rowIndex).getLname();
            case 4:
                return listPerson.get(rowIndex).getAge();
            default:
                return "";
        }
    }

    @Override
    public void setAllValue(List<Person> list){
        //Заменить массив
        listPerson.clear();
        listPerson.addAll(list);
        //метод обновления в таблице
        fireTableDataChanged();
        //метод обновления в таблице
    }

    public void setUpdateValue(Person pers){
                listPerson.set(pers.getIndex(),pers);
                //Обновляем UI таблицы
                fireTableDataChanged();
    }

    @Override
    public void setValue(Person pers) {
        listPerson.add(pers);
        fireTableDataChanged();
    }


    @Override
    public void delValue(int id) {
        for (int i = 0; i < listPerson.size(); i++) {
            if (listPerson.get(i).getId() == id) {
                listPerson.remove(id);
                fireTableDataChanged();
                return;
            }
        }

    }
}
