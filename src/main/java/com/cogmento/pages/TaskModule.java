package com.cogmento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

import static com.cogmento.utils.CommonUtil.waitUntilTime;

public class TaskModule extends BasePage {

    private final By taskTitle = By.xpath("//label[text()='Title']//following-sibling::div//input");
    private final By taskDescription = By.xpath("//label[text()='Description']//following-sibling::textarea");

    public TaskModule(WebDriver driver) {
        super(driver);
    }

    public void createTask(HashMap<String,String> data, boolean check) throws Exception {
        clickOnCreate();
        enterTaskDetails(data, false);
        clickOnSaveTask();
        if (check) {
            checkTaskHeader(data.get("Title"));
        }
    }

    public void clickOnCreate() throws Exception {
        createButton();
    }

    public void enterTaskDetails(HashMap<String,String> data, boolean isEditing) {
        if(data.containsKey("Title")){
            if (isEditing){
                    scriptAction.clearAndInputText(taskTitle,data.get("Title"));
                } else {
                scriptAction.inputText(taskTitle, data.get("Title"));
            }
        }

        if(data.containsKey("Description")){
            if (isEditing){
                scriptAction.clearAndInputText(taskDescription,data.get("Description"));
            } else {
                scriptAction.inputText(taskDescription,data.get("Description"));
            }
        }

    }



    public void editTask(HashMap<String,String> data, HashMap<String, String> newData) throws Exception {
        performTableOperation(data.get("Title"),"edit");
        waitUntilTime(2000);
        enterTaskDetails(newData, true);
        clickOnSaveTask();
    }

    public void clickOnSaveTask() throws Exception {
        saveButton();
    }

    public void checkTaskHeader(String title) throws Exception {
        checkPageHeader(title);
    }

    public void deleteTask(HashMap<String, String> data,String action) throws Exception {
        String  del = data.get("Title");
        waitUntilTime(2000);
        deleteRecord(del, action);
    }

}
