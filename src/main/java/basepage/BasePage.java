package basepage;

import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;

public class BasePage {

    protected static WebDriverWait wait;

    public BasePage() {
        wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));
    }


}
