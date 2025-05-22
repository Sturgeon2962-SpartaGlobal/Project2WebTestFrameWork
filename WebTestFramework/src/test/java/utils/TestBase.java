package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.IOException;
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

                ChromeOptions options = new ChromeOptions();
<<<<<<< HEAD
                options.addArguments("--headless=new"); // Use modern headless mode
=======
                options.addArguments("--headless=new"); // modern headless
>>>>>>> ec244b7001bf12cbbb33029c0d1d9f4881a734ae
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
//                options.addArguments("--window-size=1920,1080");
<<<<<<< HEAD
                options.addArguments("--user-data-dir=" + userDataDir);
//                options.addArguments("--start-maximized");
=======
>>>>>>> ec244b7001bf12cbbb33029c0d1d9f4881a734ae

                driver = new ChromeDriver(options);

            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();
<<<<<<< HEAD
                options.addArguments("--headless=new"); // Use modern headless mode
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
//                options.addArguments("--window-size=1920,1080");
                options.addArguments("--user-data-dir=" + userDataDir);
//                options.addArguments("--start-maximized");
=======
                options.addArguments("--headless"); // classic headless mode
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
//                options.addArguments("--width=1920");
//                options.addArguments("--height=1080");
>>>>>>> ec244b7001bf12cbbb33029c0d1d9f4881a734ae

                driver = new FirefoxDriver(options);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(url);
        }

        return driver;
    }
}
