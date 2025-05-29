package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By registerBtn = By.xpath("//button[@class='mdc-button mdc-button--raised mat-mdc-raised-button mat-unthemed mat-mdc-button-base']");
    private By userNameInput = By.cssSelector("input[placeholder*=\"Username\"]");
    private By passwordInput = By.cssSelector("input[placeholder*=\"Password\"]");
    private By submitForm = By.xpath("//form/mat-card-actions/button");
    private By errorMsg = By.xpath("/html/body/app-root/div/app-login/div/mat-card/mat-card-content/form/mat-form-field[2]/div[1]/div/div[2]");

    public void selectRegisterBtn() {
        driver.findElement(registerBtn).click();
    }

    public void CompleteLoginForm(String username, String password) {
        driver.findElement(userNameInput).sendKeys(username);
        if (password.isEmpty()) {
            driver.findElement(passwordInput).sendKeys(Keys.ENTER);
        } else {
            driver.findElement(passwordInput).sendKeys(password);

        }
    }


    public void SubmitLoginForm() throws InterruptedException {
        Thread.sleep(500);
//        driver.findElement(passwordInput).sendKeys(Keys.RETURN);
        driver.findElement(submitForm).click();
    }

    public boolean getErrorMsg() throws InterruptedException {
        Thread.sleep(250);
        WebElement errorMsgs = driver.findElement(errorMsg);
        String borderColor = errorMsgs.getCssValue("--bs-form-invalid-border-color");
        System.out.println("Border color: " + borderColor);
        return borderColor.equals("#dc3545");
    }

}
