package tricentis;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;
import pageObjects.SearchPage;
import utils.BrowserFactory;
import utils.DriverFactory;
import utils.ExcelReader;
import utils.PropReader;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import static utils.DriverFactory.getDriver;

public class TricentisTests {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browserName", "isHeadless", "remoteUrl"})
    public void setup(@Optional("chrome") String browserName,
                      @Optional("true") boolean isHeadless,
                      @Optional("") String remoteUrl) throws MalformedURLException {
        if (remoteUrl.isEmpty()){
            remoteUrl = PropReader.getProperty("remoteUrl");
        }
        WebDriver driver = BrowserFactory.createBrowser(browserName, isHeadless, remoteUrl);
        DriverFactory.setDriver(driver);
        String url = PropReader.getProperty("appurl");
        getDriver().get(url);
    }


    @Test(dataProvider = "register", priority = 1)
    public void navToRegistrationPageTest(String firstName, String lastName, String email, String password, String confirmPassword) {
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.openRegisterationPage();
        registrationPage.fillDetails(firstName, lastName, email, password, confirmPassword);
        registrationPage.clickRegistrationButton();
    }

    @Test(dataProvider = "login", priority = 2)
    public void loginTest(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateToLoginPage();
        loginPage.fillUserCredentials(username, password);
        loginPage.clickLoginBtn();
    }

    @Test(priority = 3, invocationCount = 1)
    public void searchTest() {
        SearchPage searchPage = new SearchPage();
        searchPage.search("Books");
        searchPage.navigateToBooksPage();
        searchPage.clickOnComputerBook();
    }

    @DataProvider(name = "register")
    public Object[][] registrationData() {
        try {
            return ExcelReader.getDataFromExcel("registration");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to read the excel file..." + e);
        }
    }


    @DataProvider(name = "login")
    public Object[][] loginData() {
        try {
            return ExcelReader.getDataFromExcel("login");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to read the excel file..." + e);
        }
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.removeDriver();
    }


}
