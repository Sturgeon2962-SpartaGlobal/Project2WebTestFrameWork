package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By registerBtn = By.xpath("//button[@class='mdc-button mdc-button--raised mat-mdc-raised-button mat-unthemed mat-mdc-button-base']");
    private By userNameInput = By.cssSelector("input[placeholder*=\"Username\"]");
    private By passwordInput = By.cssSelector("input[placeholder*=\"Password\"]");
    private By submitForm = By.xpath("//form/mat-card-actions/button");

    public void selectRegisterBtn() {
        driver.findElement(registerBtn).click();
    }

    public void CompleteLoginForm(String username, String password) {
        driver.findElement(userNameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
    }


    public void SubmitLoginForm() throws InterruptedException {
        Thread.sleep(500);
//        driver.findElement(passwordInput).sendKeys(Keys.RETURN);
        driver.findElement(submitForm).click();
    }
}
