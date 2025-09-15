package pageObjects;

import basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    private final static By REGISTER_XPATH = By.xpath("//a[@href='/register']");
    private final static By FIRSTNAME_ID = By.id("FirstName");
    private final static By LASTNAME_ID = By.id("LastName");
    private final static By EMAIL_ID = By.id("Email");
    private final static By PASSWORD_ID = By.id("Password");
    private final static By CONFIRM_PASSWORD_ID = By.id("ConfirmPassword");
    private final static By REGISTER_BUTTON_ID = By.id("register-button");

    public void openRegisterationPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(REGISTER_XPATH)).click();
    }

    public void fillDetails(String firstName, String lastName, String emailId, String password, String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRSTNAME_ID)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LASTNAME_ID)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_ID)).sendKeys(emailId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ID)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_PASSWORD_ID)).sendKeys(confirmPassword);
    }

    public void clickRegistrationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(REGISTER_BUTTON_ID)).click();
    }


}
