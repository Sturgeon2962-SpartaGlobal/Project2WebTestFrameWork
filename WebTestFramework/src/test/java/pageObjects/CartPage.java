package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    public WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    private By checkoutBtn = By.cssSelector(".my-2.mdc-button.mdc-button--raised.mat-mdc-raised-button.mat-warn.mat-mdc-button-base");

    public void clickCheckoutBtn() {
        driver.findElement(checkoutBtn).click();
    }
}
