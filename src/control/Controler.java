package control;

import model.Mock;
import model.Person;
import model.TableContract;
import view.factory.*;


public class Controler  implements IControler, DialogCreate.DialogCreateCallBack,
        DialogUpdate.DialogUpdateCallBack, DialogDelete.DialogDeleteCallBack{
    //То что обновляет таблицу
    private TableContract tabelConfig;
    // Интерфейс фабрики
    private IDialogFactory iDialogFactory;

    public Controler(TableContract tabelConfig) {
        // Получаем то что обновляет таблицу из панели
        this.tabelConfig = tabelConfig;
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
        tabelConfig.setAllValue(Mock.getInstance().read());
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
    public void callBackCreate(Person person) {
        // обновляем таблицу на создание
        Mock.getInstance().create(person);
        tabelConfig.setValue(person);
    }

    @Override
    public void callBackUpdate(Person person) {
        // обновляем таблицу на изменение
        tabelConfig.setUpdateValue(Mock.getInstance().updateUI(person));
    }

    @Override
    public Person eventUpdate(long id) {
        return Mock.getInstance().update(id);
    }

    @Override
    public void callBackDelete(long id) {
        Mock.getInstance().delete(id);
    }
}
