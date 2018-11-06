/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.commands;

import app.main.ApplicationManager;
import com.application.data.Article;
import com.application.data.Debtor;
import java.time.LocalDate;




/**
 *
 * @author daan-
 */
public class ProjectCommand {

    private static ProjectCommand instance;






    public static ProjectCommand getInstance() {
        if (instance == null) {
            synchronized (ProjectCommand.class) {
                if (instance == null) {
                    instance = new ProjectCommand();
                }
            }
        }
        return instance;
    }






    public ProjectCommand() {
    }






    public static Command dateCommand(LocalDate date) {
        return getInstance().createInvoiceDateCommand(date);
    }






    public static Command numberCommand(int number) {
        return getInstance().createInvoiceNumberCommand(number);
    }






    public static Command debtorCommand(Debtor debtor) {
        return getInstance().createDebtorCommand(debtor);
    }






    public static Command addArticleCommand(Article article) {
        return getInstance().createAddArticleCommand(article);
    }






    public static Command removeArticleCommand(Article article) {
        return getInstance().createRemoveArticleCommand(article);
    }






    public Command createInvoiceDateCommand(LocalDate invoiceDate) {
        return new SetInvoiceDateCommand(invoiceDate);
    }






    public Command createInvoiceNumberCommand(int invoiceNumber) {
        return new SetInvoiceNumberCommand(invoiceNumber);
    }






    public Command createDebtorCommand(Debtor debtor) {
        return new SetDebtorCommand(debtor);
    }






    public Command createAddArticleCommand(Article article) {
        return new AddArticleCommand(article);
    }






    public Command createRemoveArticleCommand(Article article) {
        return new RemoveArticleCommand(article);
    }




    /**
     * INVOICE NUMBER COMMAND
     */
    public class SetInvoiceNumberCommand implements Command {

        private final int invoiceNumber;






        public SetInvoiceNumberCommand(int invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }






        @Override
        public void execute() {
            ApplicationManager.getInstance().getProjectManager().updateInvoiceNumber(invoiceNumber);
            System.out.println("Set Invoice Number Command invoked");
        }

    }




    /**
     * INVOICE DATE COMMAND
     */
    public class SetInvoiceDateCommand implements Command {

        private final LocalDate invoiceDate;






        public SetInvoiceDateCommand(LocalDate invoiceDate) {
            this.invoiceDate = invoiceDate;
        }






        @Override
        public void execute() {
            ApplicationManager.getInstance().getProjectManager().updateInvoiceDate(invoiceDate);
            System.out.println("Set Invoice Date Command Invoked");
        }

    }




    /**
     * DEBTOR COMMAND
     */
    public class SetDebtorCommand implements Command {

        private final Debtor debtor;






        public SetDebtorCommand(Debtor debtor) {
            this.debtor = debtor;
        }






        @Override
        public void execute() {
            ApplicationManager.getInstance().getProjectManager().updateDebtor(debtor);
            System.out.println("Set Debtor Command invoked");
        }

    }




    /**
     * ADD ARTICLE COMMAND
     */
    public class AddArticleCommand implements Command {

        private final Article article;






        public AddArticleCommand(Article article) {
            this.article = article;
        }






        @Override
        public void execute() {
            ApplicationManager.getInstance().getProjectManager().addArticle(article);
            System.out.println("Add Article Command Invoked");
        }

    }




    /**
     * REMOVE ARTICLE COMMAND
     */
    public class RemoveArticleCommand implements Command {

        private final Article article;






        public RemoveArticleCommand(Article article) {
            this.article = article;
        }






        @Override
        public void execute() {
            ApplicationManager.getInstance().getProjectManager().removeArticle(article);
            System.out.println("Remove Article Command invoked");
        }

    }


}

