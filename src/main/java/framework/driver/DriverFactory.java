package framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static WebDriver webDriver;

    public static WebDriver GetDriver(WebDrivers driver) {
        switch (driver) {
            case CHROME:
                if (webDriver == null) {
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                    return webDriver;
                }
                else return webDriver;
            case FIREFOX:
                if(webDriver == null) {
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    return  webDriver;
                }
                else return webDriver;
            default:
                throw new IllegalArgumentException("The driver is not implemented :" + driver.name());
        }

    }
}
