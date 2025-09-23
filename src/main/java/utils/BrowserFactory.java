package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserFactory {


    public static WebDriver createBrowser() {
        String runEnv = PropReader.getProperty("runEnv");
        String browserName = PropReader.getProperty("browser");
        browserName = browserName.toLowerCase();
        WebDriver driver;

        if (runEnv.equals("ci")) {
            switch (browserName) {
                case "firefox" -> driver = new FirefoxDriver(new FirefoxOptions());
                case "edge" -> driver = new EdgeDriver(new EdgeOptions());
                default -> driver = new ChromeDriver(getChromeOptions());
            }
        } else {
            switch (browserName) {
                case "firefox" -> driver = new FirefoxDriver();
                case "edge" -> driver = new EdgeDriver();
                default -> driver = new ChromeDriver(getChromeOptions());
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        return driver;
    }


    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }

}
