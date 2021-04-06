package com.adidyk.frame;

import com.adidyk.model.Account;
import com.adidyk.model.AccountTableModel;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestFrame extends JFrame {

    private JTable table;

    public TestFrame() {
        super("Тестовое окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("andros", "androsdav@gmail.com", "bla"));
        accounts.add(new Account("svistun", "svistunv@gmail.com", "bla"));
        accounts.add(new Account("bondarchuk", "bondarchuk@gmail.com", "bla"));
        accounts.add(new Account("galanenko", "galanenkov@gmail.com", "bla"));
        TableModel model = new AccountTableModel(accounts);
        this.table = new JTable(model);
        getContentPane().add(new JScrollPane(table));
        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
