package utils;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static final InheritableThreadLocal<WebDriver> threadedDriver = new InheritableThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (threadedDriver.get() == null) {
            throw new IllegalStateException("WebDriver has not been set, Please set driver using DriverFactory.setDriver...");
        } else {
            return threadedDriver.get();
        }
    }

    public static void setDriver(WebDriver driver) {
        threadedDriver.set(driver);
    }

    public static void removeDriver() {
        if (threadedDriver.get() != null) {
            threadedDriver.get().quit();
            threadedDriver.remove();
        }
    }


}
