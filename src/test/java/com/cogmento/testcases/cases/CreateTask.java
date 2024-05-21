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
    private static final String SHEETNAME = "Cases";
    private final String TESTCASENAME = "TC_01";
    //private CasesPage casesPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private TasksPage taskspage;
    private String userEmailId, userPwd;
    private HashMap<String, String> testData = new HashMap<>();

    @BeforeMethod
    public void setUp() {

        // Get Test data
        userEmailId = configurationDetails.getUserName();
        userPwd = configurationDetails.getPassword();
        testData = xlsFile.getExcelRowValuesIntoMapBasedOnKey(SHEETNAME, TESTCASENAME);


        // Initiate Pages
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        taskspage = new TasksPage(getDriver());
    }

    @Test(description = "Create Case")
    public void testCaseFlow() throws Exception {
        try {

            // Login Page - Login to Application
            loginPage.loginToApp(userEmailId, userPwd);
            ExtentTestManager.getTest().pass("Logged in to application");

            // Step 1 :  Create Company
            homePage.selectEntity(EntityPanel.Tasks);
            //casesPage.createCase(testData).verifyCase(testData.get("Title"));
           taskspage.createTask("Suppu","Task Page",true);
//            taskspage.deleteTask("Suppu","Delete");
            homePage.selectEntity(EntityPanel.Tasks);
            taskspage.deleteAction("Suppu","Cancel");


        } catch (Exception e) {
            throw new CustomException(e);

        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        homePage.selectEntity(EntityPanel.Tasks);

        //casesPage.deleteAndPurgeCase(testData.get("Title"));
    }
}