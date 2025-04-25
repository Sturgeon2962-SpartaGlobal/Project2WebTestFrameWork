package stepDefs;

import com.aventstack.extentreports.reporter.FileUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.TestContextSetup;

import java.io.File;
import java.io.IOException;

public class Hooks {
    public TestContextSetup testContextSetup;
    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {
        WebDriver driver = testContextSetup.testBase.WebDriverManager();

        if (scenario.isFailed()) {
            File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");
        }
    }

    @After
    public void AfterScenario() throws IOException {
        testContextSetup.testBase.WebDriverManager().quit();
    }
}
