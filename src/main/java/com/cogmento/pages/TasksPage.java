package com.cogmento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TasksPage extends BasePage{
    public TasksPage(WebDriver driver) {
        super(driver);
    }

    private final By taskTitle = By.xpath("//label[text()='Title']//following-sibling::div//input");
    private final By taskDescription = By.xpath("//label[text()='Description']//following-sibling::textarea");
    private final By taskAssignedTo = By.xpath("//label[text()='Assigned To']//following-sibling::div");
    private final By taskAssignedVisible = By.xpath("//label[text()='Assigned To']//following-sibling::div//span[text()='Harshini Matlapudi']");
    public void createTask(String title, String description) throws Exception {
        clickOnCreateTask();
        enterTaskDetails(title, description);
        clickOnSave();
        checkTaskHeader(title);
    }

    public void clickOnCreateTask() throws Exception {
        createButton();
    }

    public void enterTaskDetails(String title, String description) {
        scriptAction.inputText(taskTitle, title);
        scriptAction.inputText(taskDescription, description);
        scriptAction.clickElement(taskAssignedTo );
        scriptAction.clickElement(taskAssignedVisible);
    }

    public void clickOnSave() throws Exception {
        saveButton();
    }

    public void checkTaskHeader(String title) throws Exception {
        checkPageHeader(title);
    }

    public void clickOnDelete(String title, String popUpValue) throws Exception {
        deleteRecord(title, popUpValue);
    }


}
