package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By registerBtn = By.xpath("//button[@class='mdc-button mdc-button--raised mat-mdc-raised-button mat-unthemed mat-mdc-button-base']");

    public void selectRegisterBtn() {
        driver.findElement(registerBtn).click();
    }
}
