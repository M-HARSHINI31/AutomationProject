package com.cogmento.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class TaskPage extends BasePage{
    public TaskPage(WebDriver driver) {
        super(driver);
    }
    private final By TaskTitle = By.xpath("(//input[@autocomplete=\"new-password\"])[1]");
    private final By TaskDescription = By.xpath("//textarea[@autocomplete=\"new-password\"]");
    private final By TaskIdentifier = By.xpath("(//input[@autocomplete=\"new-password\"])[2]");
    public void CreateTaskPage(String title, String description, String Identifier) throws Exception {
        ClickOnCreateTask();
        EnterTaskDetails(title,description,Identifier);
        ClickOnSave();
        CheckPageHeader(title);
    }
    public void ClickOnCreateTask() throws Exception {
        createButton();
    }
    public void EnterTaskDetails(String title,String description, String Identifier){
        scriptAction.inputText(TaskTitle , title);
        scriptAction.inputText(TaskDescription , description);
        scriptAction.inputText(TaskIdentifier , Identifier);
    }
    public void ClickOnSave() throws Exception {
        saveButton();
    }
    public void CheckPageHeader(String title) throws Exception {
        checkPageHeader(title);
    }
}
