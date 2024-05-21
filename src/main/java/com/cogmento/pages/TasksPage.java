package com.cogmento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TasksPage extends BasePage{

    public TasksPage(WebDriver driver) {
        super(driver);

    }
    private final By taskTitle = By.xpath("//label[text()='Title']//following-sibling::div//input");
    private final By taskDescription = By.xpath("//label[text()='Description']//following-sibling::textarea");

    public void createTask(String title, String description,boolean check) throws Exception {
        clickOnCreate();
        enterTaskDetails(title,description);
        clickOnSaveTask();
        checkTaskHeader(title);
        if(check){
            checkTaskHeader(title);
        }
    }
    public void clickOnCreate() throws Exception {
        createButton();
    }

    public void enterTaskDetails(String title, String description){
        scriptAction.inputText(taskTitle,title);
        scriptAction.inputText(taskDescription,description);

    }

    public void clickOnSaveTask() throws Exception {
        saveButton();

    }
    public void checkTaskHeader(String title) throws Exception {
        checkPageHeader(title);

    }
    public void deleteAction(String title, String action) throws Exception {
        deleteRecord(title,action);
    }



}
