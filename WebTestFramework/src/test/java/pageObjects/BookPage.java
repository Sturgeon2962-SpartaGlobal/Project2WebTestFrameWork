package pageObjects;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookPage {
    public WebDriver driver;

    public BookPage(WebDriver driver){
        this.driver = driver;
    }

    private By addToCartButton = By.xpath("/html/body/app-root/div/app-book-details/mat-card/mat-card-content/div[2]/div/app-addtocart/button");

    public void AddToCart(){
        driver.findElement(addToCartButton).click();
    }
}
