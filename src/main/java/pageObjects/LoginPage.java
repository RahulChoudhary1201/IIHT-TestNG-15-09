package pageObjects;

import basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private static final By LOGIN_XPATH = By.xpath("//a[@href='/login']");
    private static final By EMAIL_INPUT = By.id("Email");
    private static final By PASSWORD_INPUT = By.name("Password");
    private static final By LOGIN_BTN = By.xpath("//input[@value='Log in']");


    public void navigateToLoginPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_XPATH)).click();
    }

    public void fillUserCredentials(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT)).sendKeys(password);
    }

    public void clickLoginBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BTN)).click();
    }


}
