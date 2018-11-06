/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.views;

import app.main.ApplicationManager;
import com.application.data.Article;
import com.project.InvoiceProjectAccessor;
import com.project.ProjectObserver;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;




/**
 *
 * @author daan-
 */
public class InvoiceInterfaceView extends JPanel implements ProjectObserver {

    private JComboBox articleBox;
    private JButton deleteButton;
    private JButton dateButton;






    public InvoiceInterfaceView() {
        super(new GridLayout(1, 3, 10, 10));
        createComponents();
        ApplicationManager.getInstance().getProjectManager().registerObserver(this);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), BorderFactory.createEtchedBorder()));
    }






    private void createComponents() {
        articleBox = new JComboBox(new Article[]{});
        deleteButton = new JButton("Verwijderen");
        dateButton = new JButton("Datum selecteren");
        add(articleBox);
        add(deleteButton);
        add(dateButton);
        articleBox.setEnabled(false);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener((ActionEvent ae) -> {
            Article article = ApplicationManager.getInstance().getProjectManager().getArticleList().get(articleBox.getSelectedIndex());
            ApplicationManager.getInstance().getProjectManager().removeArticle(article);
        });
        dateButton.addActionListener((ActionEvent ae) -> {
            SwingUtilities.invokeLater(() -> {
                DatePicker datePicker = new DatePicker();
                datePicker.loadAndShowDatePicker();
            });
        });

    }






    @Override
    public void debtorChanged(InvoiceProjectAccessor accessor) {
    }






    @Override
    public void detailsChanged(InvoiceProjectAccessor accessor) {
    }






    @Override
    public void articlesChanged(InvoiceProjectAccessor accessor) {
        ArrayList<Article> list = accessor.getArticleList();
        if (list.size() <= 0) {
            articleBox.removeAllItems();
            articleBox.setEnabled(false);
            deleteButton.setEnabled(false);
        } else {
            articleBox.removeAllItems();
            for (int i = 0; i < list.size(); i++) {
                articleBox.addItem(list.get(i));
            }
            articleBox.setEnabled(true);
            articleBox.setSelectedIndex(0);
            deleteButton.setEnabled(true);

        }

    }

}

