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

    public void completeShippingForm() throws InterruptedException {
        Thread.sleep(100);
        driver.findElement(nameField).sendKeys("John Doe");
        Thread.sleep(100);
        driver.findElement(addressLine1).sendKeys("123 Main St");
        Thread.sleep(100);
        driver.findElement(addressLine2).sendKeys("TownCentre");
        Thread.sleep(100);
        driver.findElement(pincodeField).sendKeys("123456");
        Thread.sleep(100);
        driver.findElement(state).sendKeys("Washington");
        Thread.sleep(100);

//        driver.findElement(placeOrderBtn).click();
    }

    public void submitShippingForm() throws InterruptedException {
        WebElement button = driver.findElement(placeOrderBtn);
        Thread.sleep(250);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        Thread.sleep(250);
        button.click();
    }

    public boolean getErrorMsgs() {
        WebElement errorMsg = driver.findElement(errorMsgs);
        String borderColor = errorMsg.getCssValue("--bs-form-invalid-border-color");
        System.out.println("Border color: " + borderColor);
        return borderColor.equals("#dc3545");
    }
}
