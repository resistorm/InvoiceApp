/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.main;

import com.application.data.Article;
import com.application.data.Debtor;
import com.application.data.InvoiceDetails;
import com.application.data.InvoiceTotal;
import com.project.InvoiceProject;
import com.project.InvoiceProjectAccessor;
import com.project.InvoiceProjectController;
import com.project.ProjectObserver;
import com.project.ProjectPrinter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;




/**
 *
 * @author daan-
 */
public class ProjectManager implements InvoiceProjectAccessor, InvoiceProjectController {

    private ArrayList<ProjectObserver> observerList;
    private InvoiceProject invoiceProject;






    public ProjectManager(InvoiceProject invoiceProject) {
        observerList = new ArrayList<>();
        this.invoiceProject = invoiceProject;
    }






    @Override
    public Debtor getDebtor() {
        return invoiceProject.debtor;
    }






    @Override
    public InvoiceDetails getInvoiceDetails() {
        return invoiceProject.invoiceDetails;
    }






    @Override
    public ArrayList<Article> getArticleList() {
        return invoiceProject.articleList;
    }






    @Override
    public InvoiceTotal getInvoiceTotal() {
        return new InvoiceTotal(invoiceProject.articleList);
    }






    @Override
    public void registerObserver(ProjectObserver observer) {
        observerList.add(observer);
    }






    @Override
    public void removeObserver(ProjectObserver observer) {
        observerList.remove(observer);
    }






    public void updateInvoiceDate(LocalDate ld) {
        System.out.println("\t$invoiceProject.invoiceDetails.setInvoiceDetails(id) ");
        invoiceProject.invoiceDetails.setInvoiceDate(ld);
        System.out.println("\t$notifyDetailsChange() ");
        notifyDetailsChange();
    }






    public void updateInvoiceNumber(int number) {
        System.out.println("\t$invoiceProject.invoiceDetails.setInvoiceNumber(number); ");
        invoiceProject.invoiceDetails.setInvoiceNumber(number);
        System.out.println("\t$notifyDetailsChange(); ");
        notifyDetailsChange();
    }






    @Override
    public void updateInvoiceDetails(InvoiceDetails details) {
        System.out.println("\t$invoiceProject.invoiceDetails.setInvoiceDate(details.getLocalInvoiceDate()); ");
        invoiceProject.invoiceDetails.setInvoiceDate(details.getLocalInvoiceDate());
        System.out.println("\t$invoiceProject.invoiceDetails.setInvoiceNumber(details.getInvoiceNumber()); ");
        invoiceProject.invoiceDetails.setInvoiceNumber(details.getInvoiceNumber());
        System.out.println("\t$notifyDetailsChange(); ");
        notifyDetailsChange();
    }






    @Override
    public void updateDebtor(Debtor debtor) {
        System.out.println("\t$invoiceProject.debtor = debtor; ");
        invoiceProject.debtor = debtor;
        System.out.println("\t$notifyDebtorChange(); ");
        notifyDebtorChange();
    }






    @Override
    public void addArticle(Article article) {
        invoiceProject.articleList.add(article);
        notifyArticlesChange();
    }






    @Override
    public void removeArticle(Article article) {
        invoiceProject.articleList.remove(article);
        notifyArticlesChange();
    }






    @Override
    public void printInvoice() {
        ProjectPrinter.printProject(invoiceProject);
    }






    @Override
    public InvoiceProject getInvoiceProject() {
        return invoiceProject;
    }






    public void replaceInvoiceProject(InvoiceProject invoiceProject) {
        this.invoiceProject = invoiceProject;
        notifyInvoiceChange();
    }






    protected void notifyDebtorChange() {
        if (!observerList.isEmpty()) {
            System.out.println("\t$Iterator<ProjectObserver> iterator = observerList.iterator(); ");
            Iterator<ProjectObserver> iterator = observerList.iterator();
            System.out.println("\t$while (iterator.hasNext()) { ");
            while (iterator.hasNext()) {
                System.out.println("\t$iterator.next().debtorChanged(this); ");
                iterator.next().debtorChanged(this);
            }
        }

    }






    protected void notifyDetailsChange() {
        if (!observerList.isEmpty()) {
            Iterator<ProjectObserver> iterator = observerList.iterator();
            while (iterator.hasNext()) {
                iterator.next().detailsChanged(this);
            }
        }
    }






    protected void notifyArticlesChange() {
        if (!observerList.isEmpty()) {
            Iterator<ProjectObserver> iterator = observerList.iterator();
            while (iterator.hasNext()) {
                iterator.next().articlesChanged(this);
            }
        }
    }






    protected void notifyInvoiceChange() {
        if (!observerList.isEmpty()) {
            Iterator<ProjectObserver> iterator = observerList.iterator();
            while (iterator.hasNext()) {
                iterator.next().invoiceChanged(this);
            }
        }
    }
}

