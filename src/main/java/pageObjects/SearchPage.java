package pageObjects;

import basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {
    private static final By BOOKS_MENU_XPATH = By.xpath("//ul[@class='top-menu']//a[@href='/books']");
    private static final By COMPUTER_BOOKS_XPATH = By.xpath("//a[text()='Computing and Internet']");
    private static final By SEARCH_INPUT_ID = By.id("small-searchterms");
    private static final By SEARCH_BTN_XPATH = By.xpath("//input[@value='Search']");
    private static final By SEARCH_RESULT_XPATH = By.xpath("//div[@class='search-results']");


    public void search(String keyword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT_ID)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT_ID)).sendKeys(keyword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BTN_XPATH)).click();
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_RESULT_XPATH)).getText();
        System.out.println("Results: " + text);
    }

    public void navigateToBooksPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BOOKS_MENU_XPATH)).click();
    }

    public void clickOnComputerBook() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(COMPUTER_BOOKS_XPATH)).click();
    }

}
