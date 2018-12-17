package view;

import control.Controler;
import control.IControler;
import model.Table;
import model.TableContract;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.IOException;

public class Panel extends JPanel {
   private IControler iControler;
   private JTable tbl ;
   private JScrollPane scr ;
   private JButton creat;
   private JButton read;
   private JButton update;
   private JButton delete;
   private JButton search;
   private JButton export;
   private JButton _import;

    public Panel()
    {
        TableContract tabelConfig = new Table();
        iControler = new Controler(tabelConfig);
        setLayout(null);
        setBackground(Color.CYAN);
        tbl = new JTable((TableModel) tabelConfig);
        scr = new JScrollPane(tbl);
        scr.setBounds(10, 10, 400, 400);

        creat  =  new JButton("Create");
        read   =  new JButton("Read");
        update =  new JButton("Update");
        delete =  new JButton("Delete");
        search =  new JButton("Search");
        export =  new JButton("Export");
        _import =  new JButton("Import");

        creat.setBounds(600, 50, 100, 40);
        creat.setBackground(Color.WHITE);

        read.setBounds(600, 100, 100, 40);
        read.setBackground(Color.WHITE);

        update.setBounds(600, 150, 100, 40);
        update.setBackground(Color.WHITE);

        delete.setBounds(600, 200, 100, 40);
        delete.setBackground(Color.WHITE);

        search.setBounds(600, 250, 100, 40);
        search.setBackground(Color.WHITE);

        export.setBounds(600, 300, 100, 40);
        export.setBackground(Color.WHITE);

        _import.setBounds(600, 350, 100, 40);
        _import.setBackground(Color.WHITE);

        add(scr);
        add(creat);
        add(read);
        add(update);
        add(delete);
        add(search);
        add(export);
        add(_import);

        creat.addActionListener(v->{
            iControler.create();
        });
        read.addActionListener(v->{
          //  tbl.repaint();
            iControler.read();});
        update.addActionListener(v->{iControler.update();});
        delete.addActionListener(v->{iControler.delete();});
        search.addActionListener(v->{iControler.search();});
        export.addActionListener(v->{
            try {
                iControler.export();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        _import.addActionListener(v->{
            try {
                iControler._import();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
