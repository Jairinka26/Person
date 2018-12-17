package control;

import model.Mock;
import model.H2Bd;
import model.Person;
import model.TableContract;
import view.factory.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Controler  implements IControler, DialogCreate.DialogCreateCallBack,
        DialogUpdate.DialogUpdateCallBack, DialogDelete.DialogDeleteCallBack, DialogSearch.DialogSearchCallBack{
    //То что обновляет таблицу
    private TableContract tabelConfig;
    // Интерфейс фабрики
    private IDialogFactory iDialogFactory;

    public Controler(TableContract tabelConfig) {
        // Получаем то что обновляет таблицу из панели
        this.tabelConfig = tabelConfig;
        tabelConfig.setAllValue(H2Bd.getInstance().read());//temp try to load data on start
    }

    @Override
    public void create() {
      // получаем диалог create передаем каллбек в фабричный метод и команду по которой фабрика поймет что спечь
      iDialogFactory = FactoryDialog.getInstance().factoryMethod(this,"create");
      //подключаем диалог
      iDialogFactory.setModal(true);
      //Отображаем диалог
      iDialogFactory.setVisible(true);
    }

    @Override
    public void read() {
        tabelConfig.setAllValue(H2Bd.getInstance().read());
    }

    @Override
    public void update() {
        iDialogFactory = FactoryDialog.getInstance().factoryMethod(this,"update");
        iDialogFactory.setModal(true);
        iDialogFactory.setVisible(true);
    }

    @Override
    public void delete() {
        iDialogFactory = FactoryDialog.getInstance().factoryMethod(this,"delete");
        iDialogFactory.setModal(true);
        iDialogFactory.setVisible(true);
    }

    @Override
    public void search() {
        iDialogFactory = FactoryDialog.getInstance().factoryMethod(this,"search");
        iDialogFactory.setModal(true);
        iDialogFactory.setVisible(true);
    }

    @Override
    public void export() throws IOException {
        H2Bd.getInstance().export();
//        List<Person> listFromDb = new ArrayList<>();
//        listFromDb=H2Bd.getInstance().read();
//        int i = 0;
//        while (listFromDb.iterator().hasNext() != false){
//
//
//        }
    }

    @Override
    public void _import() throws IOException {
        H2Bd.getInstance()._import();
    }

    @Override
    public void callBackCreate(Person person) {
        // обновляем таблицу на создание
        H2Bd.getInstance().create(person);
       // Mock.getInstance().create(person);
        tabelConfig.setValue(person);
    }

    @Override
    public void callBackUpdate(Person person) {
        // обновляем таблицу на изменение
       // tabelConfig.setUpdateValue(Mock.getInstance().updateUI(person));
        tabelConfig.setUpdateValue(H2Bd.getInstance().updateUI(person));
    }

    @Override
    public Person eventUpdate(long id) {
        return H2Bd.getInstance().update(id);
//        return Mock.getInstance().update(id);
    }

    @Override
    public void callBackDelete(long id) {
//        Mock.getInstance().delete(id);
        H2Bd.getInstance().delete(id);
    }

    @Override
    public void callBackSearch(long id) {

        tabelConfig.setSearch(H2Bd.getInstance().search(id));

    }
}
