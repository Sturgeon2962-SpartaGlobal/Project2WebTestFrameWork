package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookPage {
    public WebDriver driver;

    public BookPage(WebDriver driver){
        this.driver = driver;
    }

    private By addToCartButton = By.xpath("//app-book-details/mat-card/mat-card-content/div[2]/div/app-addtocart/button");
    private By cartCount = By.xpath("//mat-toolbar/mat-toolbar-row/div[3]/button[1]/mat-icon/span");

    public void AddToCart(){
        driver.findElement(addToCartButton).click();
    }

    public int getCartCount() {
        return driver.findElements(cartCount).size();
    }
}
