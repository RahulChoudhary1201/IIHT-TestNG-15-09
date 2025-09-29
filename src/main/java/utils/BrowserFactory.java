package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserFactory {


    public static WebDriver createBrowser(String browserName, boolean isHeadless, String remoteUrl) throws MalformedURLException {
        String runEnv = PropReader.getProperty("runEnv");
//        String browserName = PropReader.getProperty("browser");
        browserName = browserName.toLowerCase();
        String osName = System.getProperty("os.name");
        WebDriver driver;

        if (runEnv.equalsIgnoreCase("remote")) {
//            String remoteUrl = PropReader.getProperty("remoteUrl");
            ChromeOptions options = new ChromeOptions();
            if (browserName.equalsIgnoreCase("chrome")) {
                options.setCapability("browserName", browserName);
            } else if (browserName.equalsIgnoreCase("firefox")) {
                options.setCapability("browserName", browserName);
            } else if (browserName.equalsIgnoreCase("edge")) {
                options.setCapability("browserName", browserName);
            }
            switch (osName) {
                case "MAC OS X" -> options.setCapability("platformName", "MAC OS X");
                case "Linux" -> options.setCapability("platformName", "Linux");
                default -> options.setCapability("platformName", "Windows 10");
            }
            driver = new RemoteWebDriver(new URL(remoteUrl), options);

        } else {
            switch (browserName) {
                case "firefox" -> driver = new FirefoxDriver();
                case "edge" -> driver = new EdgeDriver();
                default -> driver = new ChromeDriver(getChromeOptions(isHeadless));
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        return driver;
    }


    private static ChromeOptions getChromeOptions(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }

}
