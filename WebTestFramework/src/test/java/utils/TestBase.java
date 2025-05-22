package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public WebDriver driver;

    public WebDriver WebDriverManager() throws IOException {
        // Load properties
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/global.properties")) {
            prop.load(fis);
        }

        String url = prop.getProperty("QAUrl");
        String browser = System.getProperty("browser", prop.getProperty("browser"));


        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();

                String userDataDir = Files.createTempDirectory("chrome-user-data").toString();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new"); // Use modern headless mode
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
//                options.addArguments("--window-size=1920,1080");
                options.addArguments("--user-data-dir=" + userDataDir);
//                options.addArguments("--start-maximized");

                driver = new ChromeDriver(options);

            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();

                String userDataDir = Files.createTempDirectory("chrome-user-data").toString();


                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=new"); // Use modern headless mode
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
//                options.addArguments("--window-size=1920,1080");
                options.addArguments("--user-data-dir=" + userDataDir);
//                options.addArguments("--start-maximized");

                driver = new FirefoxDriver(options);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(url);
        }

        return driver;
    }
}
