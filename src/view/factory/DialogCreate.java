package view.factory;

import model.Person;
import javax.swing.*;
import java.awt.*;

/**
 * Объект Диалог создания персон, имеет:
 * родителя JDialog
 * Интерфей IDialogFactory(Используем фабрику, для приведения к одному типу с другими объектами диалога)
 * Джинерик который наследуется от T extends DialogCreate.DialogCreateCallBack,
 * имеет внутрений интерфейс,кторый служит инициализацией, и обратным вызовом.
 *
 * @param <T>
 */
public class DialogCreate<T extends DialogCreate.DialogCreateCallBack> extends JDialog
implements IDialogFactory{
    public interface DialogCreateCallBack {
        void callBackCreate(Person person);
    }

    public DialogCreate(T callBack) {

        setLayout(null);
        setTitle("From Create");
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setBounds(300, 300, 300, 300);

        JTextField d_FName = new JTextField();
        JTextField d_LName = new JTextField();
        JTextField d_Age = new JTextField();

        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");

        JLabel lbl_FName = new JLabel("Form FNAME");
        JLabel lbl_LName = new JLabel("Form LNAME");
        JLabel lbl_Age = new JLabel("Form AGE");

        d_FName.setBounds(30, 45, 120, 20);
        d_LName.setBounds(30, 95, 120, 20);
        d_Age.setBounds(30, 150, 120, 20);

        ok.setBounds(160, 30, 100, 25);
        ok.setBackground(Color.WHITE);

        cancel.setBounds(160, 70, 100, 25);
        cancel.setBackground(Color.WHITE);

        lbl_FName.setBounds(30, 30, 120, 10);
        lbl_LName.setBounds(30, 75, 120, 10);
        lbl_Age.setBounds(30, 135, 120, 10);

        add(d_FName);
        add(d_LName);
        add(d_Age);

        add(ok);
        add(cancel);

        add(lbl_FName);
        add(lbl_LName);
        add(lbl_Age);


        ok.addActionListener(e -> {
            //При клике на ок создание персоны без айдишника, и передача обратному вызову в контролер.
            callBack.callBackCreate(new Person(
                    d_FName.getText(),
                    d_LName.getText(),
                    Long.parseLong(d_Age.getText())
            ));

           setVisible(false);
        });

        cancel.addActionListener(e -> setVisible(false));

    }

    @Override
    public void setModal(boolean modal) {
        super.setModal(modal);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
