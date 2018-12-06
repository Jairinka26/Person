package view.factory;

import model.Person;

import javax.swing.*;
import java.awt.*;

public class DialogSearch<T extends DialogSearch.DialogSearchCallBack> extends JDialog
        implements IDialogFactory {
    private int index;
    public interface DialogSearchCallBack {
        void callBackSearch(long id);

        //      Person eventSearch(long id);
    }

    public DialogSearch(T callBack) {
        setLayout(null);
        setTitle("From Search");
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setBounds(300, 300, 300, 300);

        JTextField d_Id = new JTextField();

        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");

        JLabel lbl_id = new JLabel("Form ID");

        d_Id.setBounds(30, 30, 120, 20);

        ok.setBounds(160, 70, 100, 25);
        ok.setBackground(Color.WHITE);

        cancel.setBounds(160, 110, 100, 25);
        cancel.setBackground(Color.WHITE);

        lbl_id.setBounds(30, 10, 120, 10);

        add(d_Id);

        add(ok);
        add(cancel);

        add(lbl_id);

        ok.addActionListener(e -> {
            callBack.callBackSearch(Long.parseLong(d_Id.getText()));
            setVisible(false);
        });

        cancel.addActionListener(e -> setVisible(false));

    }


}
