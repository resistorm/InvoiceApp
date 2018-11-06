/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.main;

import com.application.common.DataContainerObserver;
import com.application.core.App;
import com.application.core.Base;
import com.application.data.Address;
import com.application.data.Configuration;
import com.application.data.Debtor;
import com.application.data.InvoiceDetails;
import com.application.data.Product;
import com.project.InvoiceProject;
import com.project.ProjectObserver;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class ApplicationManager {

    private static ApplicationManager instance;
    private App app;
    private ProjectManager projectManager;






    public static ApplicationManager getInstance() {
        if (instance == null) {
            synchronized (ApplicationManager.class) {
                if (instance == null) {
                    instance = new ApplicationManager();
                    //
                    //
                    //
                    //REGISTER PROJECT OBSERVER PRINTER FOR DEBUGGING PURPOSES
                    ProjectObserverPrinter pop = new ProjectObserverPrinter();
                    instance.registerProjectObserver(pop);
                }
            }
        }
        System.out.println("\t$return instance; ");
        return instance;
    }






    private ApplicationManager() {
        this.app = new App();
        createNewProject();
    }






    public ProjectManager getProjectManager() {
        System.out.println("\t$return projectManager ");
        return projectManager;
    }






    public void createNewProject() {
        int invoiceNumber = app.configBase.getInvoiceCounter();
        InvoiceDetails invoiceDetails = new InvoiceDetails(invoiceNumber);
        Debtor debtor = new Debtor("", "", new Address("", "", "", ""));
        InvoiceProject project = new InvoiceProject(invoiceDetails, debtor);
        if (projectManager == null) {
            projectManager = new ProjectManager(project);
        } else {
            projectManager.replaceInvoiceProject(project);
        }
    }






    public void incrementInvoiceCounter() {
        app.configBase.incrementInvoiceCounter();
        app.storeToFile(Base.CONFIG);
    }






    public ArrayList<Debtor> createNewDebtorList() {
        ArrayList<Debtor> debtorList = new ArrayList<>();
        int size = app.debtorBase.size();
        for (int i = 0; i < size; i++) {
            debtorList.add(app.debtorBase.get(i));
        }
        return debtorList;
    }






    public ArrayList<Product> createNewProductList() {
        ArrayList<Product> productList = new ArrayList<>();
        int size = app.productBase.size();
        for (int i = 0; i < size; i++) {
            productList.add(app.productBase.get(i));
        }
        return productList;
    }






    public InvoiceDetails createNewInvoiceDetails() {
        int invoiceNumber = app.configBase.getInvoiceCounter();
        app.configBase.incrementInvoiceCounter();
        app.storeToFile(Base.CONFIG);
        return new InvoiceDetails(invoiceNumber);
    }






    public Debtor createEmptyDebtor() {
        return new Debtor("", "", new Address("", "", "", ""));
    }






    public void addNewDebtorEntry(Debtor debtor) {
        app.debtorBase.add(debtor);
        app.storeToFile(Base.DEBTOR);
        app.debtorBase.notifyDataContainerObservers();
    }






    public void addNewProductEntry(Product product) {
        app.productBase.add(product);
        app.storeToFile(Base.PRODUCT);
        app.productBase.notifyDataContainerObservers();
    }






    public void registerProjectObserver(ProjectObserver observer) {
        projectManager.registerObserver(observer);
    }






    public void registerDebtorDataContainerObserver(DataContainerObserver<Debtor> observer) {
        app.debtorBase.registerDataContainerObserver(observer);
    }






    public void registerProductDataContainerObserver(DataContainerObserver<Product> observer) {
        app.productBase.registerDataContainerObserver(observer);
    }






    public void registerConfigDataContainerObserver(DataContainerObserver<Configuration> observer) {
        app.configBase.registerDataContainerObserver(observer);
    }






    public void removeProjectObserver(ProjectObserver observer) {
        projectManager.removeObserver(observer);
    }






    public void removeDebtorDataContainerObserver(DataContainerObserver<Debtor> observer) {
        app.debtorBase.removeDataContainerObserver(observer);
    }






    public void removeProductDataContainerObserver(DataContainerObserver<Product> observer) {
        app.productBase.removeDataContainerObserver(observer);
    }






    public void removeConfigDataContainerObserver(DataContainerObserver<Configuration> observer) {
        app.configBase.removeDataContainerObserver(observer);
    }

}

