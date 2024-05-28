package com.cogmento.testcases.cases;

import com.cogmento.pages.*;
import com.cogmento.reporting.ExtentTestManager;
import com.cogmento.testcases.BaseTest;
import com.cogmento.utils.CustomException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class CreateTask extends BaseTest {
    private static final String SHEETNAME = "Tasks";
    private final String TESTCASENAME = "TC-01";
    //private CasesPage casesPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private TaskModule taskmodule;
    private String userEmailId, userPwd;
    private HashMap<String, String> testData = new HashMap<>();
    private HashMap<String, String> newTestData = new HashMap<>();

//    public HashMap createTestData() throws IOException {
////        HashMap<String,String> data = new HashMap<>();
////        data.put("Title","Suppuuu");
////         data.put("Description", "This is task page");
//        HashMap<String,String> data = new HashMap<>();
//        File file = new File("C:\\Users\\Supriya\\Downloads\\tasks.xlsx");
//        FileInputStream fis = new FileInputStream(file);
//        XSSFWorkbook workbook = new XSSFWorkbook(fis);
//        XSSFSheet sheet = workbook.getSheet("Tasks");
//        for(int i=1;i<=sheet.getLastRowNum();i++){
//            String key = sheet.getRow(i).getCell(0).getStringCellValue();
//            String value = sheet.getRow(i).getCell(1).getStringCellValue();
//            data.put(key,value);
//        }
//         return data;
//    }

    @BeforeSuite
    public void suiteSetUp() {
        testData = xlsFile.getExcelRowValuesIntoMapBasedOnKey(SHEETNAME, TESTCASENAME);
    }

    @BeforeMethod
    public void setUp() {

        // Get Test data
        userEmailId = configurationDetails.getUserName();
        userPwd = configurationDetails.getPassword();


        // Initiate Pages
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        taskmodule = new TaskModule(getDriver());
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
            HashMap<String, String> data = testData;

            taskmodule.createTask(data,true);



        } catch (Exception e) {
            throw new CustomException(e);

        }
    }


@Test(description = "Edit Action")
public void testCaseFlowEdit() throws CustomException {
        try{
            newTestData.put("Title","Jaiiiiiiii");

            loginPage.loginToApp(userEmailId,userPwd);
            ExtentTestManager.getTest().pass("Logged in to application");
            homePage.selectEntity(EntityPanel.Tasks);
            HashMap<String,String> data = testData;
            taskmodule.createTask(data, true);
            homePage.selectEntity(EntityPanel.Tasks);
            taskmodule.editTask(data, newTestData);

        } catch (Exception e) {
            throw new CustomException(e);
        }
}
@Test(description = "MaxCharLength")
public void testCaseFlowMaxLen() throws CustomException{
        try{
            loginPage.loginToApp(userEmailId,userPwd);
            ExtentTestManager.getTest().pass("Logged in to application");
            homePage.selectEntity(EntityPanel.Tasks);
            HashMap<String,String> data = testData;
            data.put("Title","Art is a powerful medium that transcends cultural and linguistic barriers, fostering a universal dialogue. Through various forms like painting, sculpture, music, and dance, art expresses the complexities of human emotions and experiences. It challenges perspectives, evokes empathy, and inspires change.");
taskmodule.createTask(data,false);
taskmodule.verifyToastMessage("Title is longer than 250 characters");

        }
        catch (Exception e) {
            throw new CustomException(e);

        }
}
@Test(description="Delete Action")
public void testCaseFlow2() throws Exception{
    try{
        loginPage.loginToApp(userEmailId,userPwd);
        ExtentTestManager.getTest().pass("Logged in to application");
        homePage.selectEntity(EntityPanel.Tasks);
        HashMap<String,String> data = testData;

//       taskmodule.createTask(data,true);
        taskmodule.deleteTask(data,"Delete" );

    }
    catch(Exception e){
        throw new CustomException(e);
    }
}


    @Test(description = "No Mandatory")
    public void testCaseFlow3() throws Exception{
        try{
            loginPage.loginToApp(userEmailId,userPwd);
            ExtentTestManager.getTest().pass("Logged in to application");
            homePage.selectEntity(EntityPanel.Tasks);
            HashMap<String,String> data = testData;
            data.put("Title","");
            taskmodule.createTask(data,false);
            taskmodule.verifyErrorMessage("The field Title is required.");
        }
        catch(Exception e){
            throw  new CustomException(e);
        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
       homePage.selectEntity(EntityPanel.Tasks);

        //casesPage.deleteAndPurgeCase(testData.get("Title"));
    }
}