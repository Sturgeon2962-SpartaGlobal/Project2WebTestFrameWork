package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckoutPage {
    public WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private By nameField = By.xpath("//form/mat-form-field[1]/div[1]/div/div[2]/input");
    private By addressLine1 = By.xpath("//form/mat-form-field[2]/div[1]/div/div[2]/input");
    private By addressLine2 = By.xpath("//form/mat-form-field[3]/div[1]/div/div[2]/input");
    private By pincodeField = By.xpath("//form/mat-form-field[4]/div[1]/div/div[2]/input");
    private By state = By.xpath("//form/mat-form-field[5]/div[1]/div/div[2]/input");
    private By placeOrderBtn = By.xpath("//form/mat-card-actions/button[1]");
    private By errorMsgs = By.cssSelector(".ng-untouched.ng-pristine.ng-invalid.ng-submitted");

    public void completeShippingForm() {
        driver.findElement(nameField).sendKeys("John Doe");
        driver.findElement(addressLine1).sendKeys("123 Main St");
        driver.findElement(addressLine2).sendKeys("TownCentre");
        driver.findElement(pincodeField).sendKeys("123456");
        driver.findElement(state).sendKeys("Washington");
//        driver.findElement(placeOrderBtn).click();
    }

    public void submitShippingForm() throws InterruptedException {
        WebElement button = driver.findElement(placeOrderBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector(
                                ".mdc-button.mdc-button--raised.mat-mdc-raised-button.mat-primary.mat-mdc-button-base"
                        )
                )
        );
        button.click();
    }

    public boolean getErrorMsgs() {
        WebElement errorMsg = driver.findElement(errorMsgs);
        String borderColor = errorMsg.getCssValue("--bs-form-invalid-border-color");
        System.out.println("Border color: " + borderColor);
        return borderColor.equals("#dc3545");
    }
}
