package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.nio.file.Files;

public class TestBase {
    public WebDriver driver;

    public WebDriver WebDriverManager() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String url = prop.getProperty("QAUrl");
        String browser_properties = prop.getProperty("browser");
        String browser_maven = System.getProperty("browser");

        String browser = browser_maven != null ? browser_maven : browser_properties;

        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();

                // Create a temporary directory for Chrome user data
                String userDataDir = Files.createTempDirectory("chrome-user-data").toString();

                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless=new"); // Use modern headless mode
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
//                options.addArguments("--window-size=1920,1080");
                options.addArguments("--user-data-dir=" + userDataDir);
                options.addArguments("--start-maximized");

                driver = new ChromeDriver(options);

            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();

                String userDataDir = Files.createTempDirectory("firefox-user-data").toString();


                driver = new FirefoxDriver();
                FirefoxOptions options = new FirefoxOptions();
//                options.addArguments("--headless=new"); // Use modern headless mode
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
//                options.addArguments("--window-size=1920,1080");
                options.addArguments("--user-data-dir=" + userDataDir);
                options.addArguments("--start-maximized");
            }

//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            Thread.sleep(1000);
            driver.get(url);
        }

        return driver;
    }
}
