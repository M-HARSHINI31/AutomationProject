package com.cogmento.testcases.cases;

import com.cogmento.pages.*;
import com.cogmento.reporting.ExtentTestManager;
import com.cogmento.testcases.BaseTest;
import com.cogmento.utils.CustomException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CreateTask extends BaseTest {
    private static final String SHEETNAME = "Tasks";
    private final String TESTCASENAME = "TC_01";
    //private CasesPage casesPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private TasksPage tasksPage;
    private String userEmailId, userPwd;
    private HashMap<String, String> testData = new HashMap<>();

    @BeforeMethod
    public void setUp() {

        // Get Test data
        userEmailId = configurationDetails.getUserName();
        userPwd = configurationDetails.getPassword();
//        testData = xlsFile.getExcelRowValuesIntoMapBasedOnKey(SHEETNAME, TESTCASENAME);


        // Initiate Pages
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        tasksPage = new TasksPage(getDriver());
        //casesPage = new CasesPage(getDriver());
    }

    @Test(description = "Create Task")
    public void testCaseFlow() throws Exception {
        try {

            // Login Page - Login to Application
            loginPage.loginToApp(userEmailId, userPwd);
            ExtentTestManager.getTest().pass("Logged in to application");

            // Step 1 :  Create Task
            homePage.selectEntity(EntityPanel.Tasks);
            tasksPage.createTask("Harshini", "Qualizeal Employee");
            //casesPage.createCase(testData).verifyCase(testData.get("Title"));

        } catch (Exception e) {
            throw new CustomException(e);

        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        homePage.selectEntity(EntityPanel.Tasks);
        tasksPage.clickOnDelete("Harshini", "Delete");
        //casesPage.deleteAndPurgeCase(testData.get("Title"));
    }
}