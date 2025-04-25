package utils;

import org.openqa.selenium.WebDriver;
import pageObjects.PageObjectManager;

public class TestContextSetup {
    public WebDriver driver;
    public PageObjectManager pageObjectManager;
    public TestBase testBase;
    public GenericUtils genericUtils;
    public TestContextSetup() throws Exception {
        this.testBase = new TestBase();
        this.pageObjectManager = new PageObjectManager(testBase.WebDriverManager());
        this.genericUtils = new GenericUtils(testBase.WebDriverManager());
    }
}
